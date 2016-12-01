package com.expertsoft.core.dao;

import com.expertsoft.core.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcOrderDao implements OrderDao {

    private static final String SELECT_QUERY =
            "SELECT * FROM PUBLIC.PHONIFY_ORDER AS ORD " +
                    "JOIN ORDER_ITEM ON ORDER_ITEM.ORDER_FK=ORD.KEY " +
                    "WHERE ORD.KEY=?";


    private final static String INSERT_QUERY = "INSERT INTO PHONE (price, model, color) VALUES (?, ?, ?)";
    private final static String SELECT_ALL_QUERY = "SELECT  * FROM PHONE";

    @Autowired
    private JdbcOperations jdbcOperations;

    public Order getOrder(long key) {
        //jdbcOperations.query();
        return null;
    }

    public void saveOrder(Order order) {

    }

    public List<Order> findAll() {
        return null;
    }

    public void close() {

    }

    private static class PhonifyOrderRowMapper implements RowCallbackHandler {
        private Order order = null;

        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
        }

        public void processRow(ResultSet resultSet) throws SQLException {

        }
    }
}
