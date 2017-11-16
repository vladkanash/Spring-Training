package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.OrderDao;
import com.expertsoft.core.dao.OrderItemDao;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class JdbcOrderDao implements OrderDao {

    private static final String SELECT_ALL_QUERY = "SELECT  * FROM PHONIFY_ORDER";
    private static final String SELECT_QUERY = "SELECT * FROM PHONIFY_ORDER WHERE `KEY`=?";
    private static final String ORDERS_FOR_USER_QUERY = "SELECT * FROM PHONIFY_ORDER WHERE USERNAME=?";
    private static final String UPDATE_QUERY = "UPDATE PHONIFY_ORDER SET delivered=1 WHERE `KEY`=?";

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Order> orderRowMapper;
    private final SimpleJdbcInsert jdbcInsert;
    private final OrderItemDao orderItemDao;

    @Autowired
    public JdbcOrderDao(JdbcOperations jdbcOperations,
                        RowMapper<Order> orderRowMapper,
                        DataSource dataSource,
                        OrderItemDao orderItemDao) {
        this.jdbcOperations = jdbcOperations;
        this.orderRowMapper = orderRowMapper;
        this.orderItemDao = orderItemDao;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.ORDER_TABLE)
                .usingGeneratedKeyColumns(JdbcConstants.ORDER_KEY_COLUMN);
    }

    @Override
    public Order getOrder(long key) {
        try {
            return jdbcOperations.queryForObject(SELECT_QUERY, orderRowMapper, key);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void saveOrder(Order order) {
        final Map<String, Object> parameters = new HashMap<>(8);
        parameters.put(JdbcConstants.ORDER_FIRST_NAME_COLUMN, order.getFirstName());
        parameters.put(JdbcConstants.ORDER_LAST_NAME_COLUMN, order.getLastName());
        parameters.put(JdbcConstants.ORDER_TOTAL_PRICE_COLUMN, order.getTotalPrice());
        parameters.put(JdbcConstants.ORDER_CONTACT_PHONE_COLUMN, order.getContactPhone());
        parameters.put(JdbcConstants.ORDER_DELIVERY_ADDRESS_COLUMN, order.getDeliveryAddress());
        parameters.put(JdbcConstants.ORDER_SHIPPING_PRICE_COLUMN, order.getShippingPrice());
        parameters.put(JdbcConstants.ORDER_DELIVERED_COLUMN_NAME, order.isDelivered());

        User user = order.getUser();
        if (null != user) {
            parameters.put(JdbcConstants.ORDER_USER_COLUMN_NAME, user.getUsername());
        }

        final Number newId = jdbcInsert.executeAndReturnKey(parameters);
        order.setKey(newId.longValue());

        if (null != order.getOrderItems()) {
            for (final OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
                orderItemDao.saveOrderItem(item);
            }
        }
    }

    @Override
    public List<Order> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY, orderRowMapper);
    }

    @Override
    public void setDeliveredState(long orderKey) {
       jdbcOperations.update(UPDATE_QUERY, orderKey);
    }

    @Override
    public List<Order> getOrdersForUser(String username) {
        return jdbcOperations.query(ORDERS_FOR_USER_QUERY, orderRowMapper, username);
    }
}
