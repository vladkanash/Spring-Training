package com.expertsoft.core.dao;

import com.expertsoft.core.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@ComponentScan(basePackages = "com.expertsoft.core")
public class JdbcPhoneDao implements PhoneDao {

    private final static String SELECT_QUERY = "SELECT * FROM PHONE WHERE key=?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM PHONE";

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcPhoneDao(JdbcOperations jdbcOperations, DataSource dataSource) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PHONE")
                .usingGeneratedKeyColumns("KEY");
    }

    public Phone getPhone(long key) {
        return jdbcOperations.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Phone.class),
                key);
    }

    public Phone savePhone(Phone phone) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("PRICE", phone.getPrice());
        parameters.put("MODEL", phone.getModel());
        parameters.put("COLOR", phone.getColor());
        Number newId = jdbcInsert.executeAndReturnKey(parameters);
        phone.setKey(newId.longValue());
        return phone;
    }

    public List<Phone> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY,
                new BeanPropertyRowMapper<>(Phone.class));
    }

    public void close() {

    }
}
