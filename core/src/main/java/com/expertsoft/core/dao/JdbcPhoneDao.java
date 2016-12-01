package com.expertsoft.core.dao;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@ComponentScan(basePackages = "com.expertsoft.core")
public class JdbcPhoneDao implements PhoneDao {

    private final static String SELECT_QUERY = "SELECT * FROM PHONE WHERE key=?";
    private final static String INSERT_QUERY = "INSERT INTO PHONE (price, model, color) VALUES (?, ?, ?)";
    private final static String SELECT_ALL_QUERY = "SELECT  * FROM PHONE";

    @Autowired
    private JdbcOperations jdbcOperations;

    public Phone getPhone(long key) {
        return jdbcOperations.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<Phone>(Phone.class),
                key);
    }

    public void savePhone(Phone phone) {
        jdbcOperations.update(INSERT_QUERY,
                phone.getPrice(),
                phone.getModel(),
                phone.getColor());
    }

    public List<Phone> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY,
                new BeanPropertyRowMapper<Phone>(Phone.class));
    }

    public void close() {

    }
}
