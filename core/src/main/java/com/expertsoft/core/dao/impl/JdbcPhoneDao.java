package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.PhoneDao;
import com.expertsoft.core.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class JdbcPhoneDao implements PhoneDao {

    private final static String SELECT_QUERY = "SELECT * FROM PHONE WHERE ID=?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM PHONE";

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcPhoneDao(JdbcOperations jdbcOperations, DataSource dataSource) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.PHONE_TABLE)
                .usingGeneratedKeyColumns(JdbcConstants.PHONE_ID_COLUMN);
    }

    public Phone getPhone(long key) {
        try {
            return jdbcOperations.queryForObject(SELECT_QUERY,
                    new BeanPropertyRowMapper<>(Phone.class),
                    key);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void savePhone(Phone phone) {
        final Map<String, Object> parameters = new HashMap<>(3);
        parameters.put(JdbcConstants.PHONE_PRICE_COLUMN, phone.getPrice());
        parameters.put(JdbcConstants.PHONE_MODEL_COLUMN, phone.getModel());
        parameters.put(JdbcConstants.PHONE_COLOR_COLUMN, phone.getColor());
        final Number newId = jdbcInsert.executeAndReturnKey(parameters);
        phone.setId(newId.longValue());
    }

    public List<Phone> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY,
                new BeanPropertyRowMapper<>(Phone.class));
    }
}
