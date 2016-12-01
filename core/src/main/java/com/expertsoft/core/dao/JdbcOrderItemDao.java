package com.expertsoft.core.dao;

import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcOrderItemDao implements OrderItemDao {

    private final static String SELECT_FOR_ORDER_QUERY = "SELECT * FROM ORDER_ITEM WHERE ORDER_FK=?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM ORDER_ITEM";
    private final static String SELECT_QUERY = "SELECT * FROM ORDER_ITEM WHERE KEY=?";
    private final static String INSERT_QUERY = "INSERT INTO ORDER_ITEM (key, phone_fk, quantity) VALUES (?, ?, ?)";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private RowMapper<OrderItem> orderItemRowMapper;

    public List<OrderItem> getItemsForOrder(long orderKey) {
        return jdbcOperations.query(SELECT_FOR_ORDER_QUERY,
                orderItemRowMapper,
                orderKey);
    }

    public List<OrderItem> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY,
                orderItemRowMapper);
    }

    public void saveOrderItem(OrderItem item) {
        jdbcOperations.update(INSERT_QUERY,
                item.getKey(),
                item.getPhone().getKey(),
                item.getQuantity());
    }

    public OrderItem getOrderItem(long key) {
        return jdbcOperations.queryForObject(SELECT_QUERY,
                orderItemRowMapper,
                key);
    }

    public void close() {

    }
}
