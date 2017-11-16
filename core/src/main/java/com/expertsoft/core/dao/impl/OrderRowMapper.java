package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.OrderItemDao;
import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
class OrderRowMapper implements RowMapper<Order> {

    private final OrderItemDao orderItemDao;
    private final UserDao userDao;

    @Autowired
    public OrderRowMapper(OrderItemDao orderItemDao, UserDao userDao) {
        this.orderItemDao = orderItemDao;
        this.userDao = userDao;
    }

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        final Order order = new Order();
        final long orderKey = resultSet.getLong(JdbcConstants.ORDER_ID_COLUMN);
        final List<OrderItem> orderItems = orderItemDao.getItemsForOrder(orderKey);
        if (null != orderItems) {
            for (final OrderItem item : orderItems) {
                item.setOrder(order);
            }
        }

        final String username = resultSet.getString(JdbcConstants.ORDER_USER_COLUMN_NAME);
        final User user = userDao.getUser(username);

        order.setUser(user);
        order.setOrderItems(orderItems);

        order.setId(orderKey);
        order.setFirstName(resultSet.getString(JdbcConstants.ORDER_FIRST_NAME_COLUMN));
        order.setLastName(resultSet.getString(JdbcConstants.ORDER_LAST_NAME_COLUMN));
        order.setContactPhone(resultSet.getString(JdbcConstants.ORDER_CONTACT_PHONE_COLUMN));
        order.setDeliveryAddress(resultSet.getString(JdbcConstants.ORDER_DELIVERY_ADDRESS_COLUMN));
        order.setTotalPrice(resultSet.getBigDecimal(JdbcConstants.ORDER_TOTAL_PRICE_COLUMN));
        order.setShippingPrice(resultSet.getBigDecimal(JdbcConstants.ORDER_SHIPPING_PRICE_COLUMN));
        order.setDelivered(resultSet.getBoolean(JdbcConstants.ORDER_DELIVERED_COLUMN_NAME));
        return order;
    }
}
