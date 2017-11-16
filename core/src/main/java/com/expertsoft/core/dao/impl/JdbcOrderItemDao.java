package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.OrderItemDao;
import com.expertsoft.core.model.Order;
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
class JdbcOrderItemDao implements OrderItemDao {

    private final static String SELECT_FOR_ORDER_QUERY = "SELECT * FROM ORDER_ITEM WHERE ORDER_FK=?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM ORDER_ITEM";
    private final static String SELECT_QUERY = "SELECT * FROM ORDER_ITEM WHERE ID=?";

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<OrderItem> orderItemRowMapper;

    @Autowired
    public JdbcOrderItemDao(JdbcOperations jdbcOperations,
                            DataSource dataSource,
                            RowMapper<OrderItem> orderItemRowMapper) {
        this.jdbcOperations = jdbcOperations;
        this.orderItemRowMapper = orderItemRowMapper;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.ORDER_ITEM_TABLE)
                .usingGeneratedKeyColumns(JdbcConstants.ORDER_ITEM_ID_COLUMN);
    }

    public List<OrderItem> getItemsForOrder(long orderKey) {
        return jdbcOperations.query(SELECT_FOR_ORDER_QUERY, orderItemRowMapper, orderKey);
    }

    public List<OrderItem> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY, orderItemRowMapper);
    }

    public void saveOrderItem(OrderItem item) {
        final Map<String, Object> parameters = new HashMap<>(3);

        parameters.put(JdbcConstants.ORDER_ITEM_PHONE_COLUMN, item.getPhone().getId());
        parameters.put(JdbcConstants.ORDER_ITEM_QUANTITY_COLUMN, item.getQuantity());
        final Order order = item.getOrder();
        if (null != order) {
            parameters.put(JdbcConstants.ORDER_ITEM_ORDER_COLUMN, order.getId());
        }
        final Number newId = jdbcInsert.executeAndReturnKey(parameters);
        item.setId(newId.longValue());
    }

    public OrderItem getOrderItem(long key) {
        return jdbcOperations.queryForObject(SELECT_QUERY, orderItemRowMapper, key);
    }
}
