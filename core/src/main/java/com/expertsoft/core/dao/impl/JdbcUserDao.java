package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class JdbcUserDao implements UserDao {

    private final static String SELECT_USER_QUERY = "SELECT * FROM PHONIFY_USERS WHERE username=?";
    private final static String SELECT_ALL_USERS_QUERY = "SELECT * FROM PHONIFY_USERS";
    private final static String SELECT_AUTHORITIES_QUERY = "SELECT AUTHORITY FROM PHONIFY_AUTHORITIES WHERE username=?";

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert usersInsert;
    private final SimpleJdbcInsert authoritiesInsert;

    @Autowired
    public JdbcUserDao(JdbcOperations jdbcOperations, DataSource dataSource) {
        this.jdbcOperations = jdbcOperations;
        this.usersInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.USERS_TABLE);
        this.authoritiesInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.AUTHORITIES_TABLE);
    }

    @Override
    public User getUser(String username) {
        User user;
        try {
            user = jdbcOperations.queryForObject(SELECT_USER_QUERY,
                    new BeanPropertyRowMapper<>(User.class),
                    username);
            getAuthoritiesForUser(user);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public void saveUser(User user) throws UserExistsException {
        if (user.getPassword() == null || user.getUsername() == null) {
            throw new IllegalArgumentException("Cannot save user, username and/or password is null");
        }

        final Map<String, Object> parameters = new HashMap<>(3);
        parameters.put(JdbcConstants.USERS_USERNAME_COLUMN, user.getUsername());
        parameters.put(JdbcConstants.USERS_PASSWORD_COLUMN, user.getPassword());
        parameters.put(JdbcConstants.USERS_ENABLED_COLUMN, user.isEnabled());

        try {
            usersInsert.execute(parameters);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistsException(user.getUsername());
        }

        for (final User.Authority authority : user.getAuthorities()) {
            parameters.clear();
            parameters.put(JdbcConstants.AUTHORITIES_USERNAME_COLUMN, user.getUsername());
            parameters.put(JdbcConstants.AUTHORITIES_AUTHORITY_COLUMN, authority);
            authoritiesInsert.execute(parameters);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            final List<User> users = jdbcOperations.query(SELECT_ALL_USERS_QUERY,
                    new BeanPropertyRowMapper<>(User.class));
            for (final User user : users) {
                getAuthoritiesForUser(user);
            }
            return users;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    private void getAuthoritiesForUser(final User user) {
        SqlRowSet auth = jdbcOperations.queryForRowSet(SELECT_AUTHORITIES_QUERY, user.getUsername());
        while (auth.next()) {
            final String authority = auth.getString(JdbcConstants.AUTHORITIES_AUTHORITY_COLUMN);
            user.grantAuthority(User.Authority.valueOf(authority));
        }
    }
}
