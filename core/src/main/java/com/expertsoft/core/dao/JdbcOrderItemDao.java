package com.expertsoft.core.dao;

import com.expertsoft.core.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderItemDao implements OrderItemDao {

    private final static String SELECT_FOR_ORDER_QUERY = "SELECT * FROM ORDER_ITEM WHERE ORDER_FK=?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM ORDER_ITEM";
    private final static String SELECT_QUERY = "SELECT * FROM ORDER_ITEM WHERE KEY=?";

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert insertActor;
    private final RowMapper<OrderItem> orderItemRowMapper;

    @Autowired
    public JdbcOrderItemDao(JdbcOperations jdbcOperations, DataSource dataSource, RowMapper<OrderItem> orderItemRowMapper) {
        this.jdbcOperations = jdbcOperations;
        this.orderItemRowMapper = orderItemRowMapper;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("ORDER_ITEM")
                .usingGeneratedKeyColumns("KEY");
    }

    public List<OrderItem> getItemsForOrder(long orderKey) {
        return jdbcOperations.query(SELECT_FOR_ORDER_QUERY,
                orderItemRowMapper, orderKey);
    }

    public List<OrderItem> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY,
                orderItemRowMapper);
    }

    public OrderItem saveOrderItem(OrderItem item) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("PHONE_FK", item.getPhone().getKey());
        parameters.put("QUANTITY", item.getQuantity());
        Number newId = insertActor.executeAndReturnKey(parameters);
        item.setKey(newId.longValue());
        return item;
    }

    public OrderItem getOrderItem(long key) {
        return jdbcOperations.queryForObject(SELECT_QUERY,
                orderItemRowMapper, key);
    }

    public void close() {

    }
}
