package com.expertsoft.core.dao;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderRowMapper implements RowMapper<Order> {

    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderRowMapper(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        long orderKey = resultSet.getLong(JdbcConstants.ORDER_KEY_COLUMN);
        List<OrderItem> orderItems = orderItemDao.getItemsForOrder(orderKey);
        if (null != orderItems) {
            for (OrderItem item : orderItems) {
                item.setOrder(order);
            }
        }
        order.setOrderItems(orderItems);

        order.setKey(orderKey);
        order.setFirstName(resultSet.getString(JdbcConstants.ORDER_FIRST_NAME_COLUMN));
        order.setLastName(resultSet.getString(JdbcConstants.ORDER_LAST_NAME_COLUMN));
        order.setContactPhone(resultSet.getString(JdbcConstants.ORDER_CONTACT_PHONE_COLUMN));
        order.setDeliveryAddress(resultSet.getString(JdbcConstants.ORDER_DELIVERY_ADDRESS_COLUMN));
        order.setTotalPrice(resultSet.getBigDecimal(JdbcConstants.ORDER_TOTAL_PRICE_COLUMN));
        return order;
    }
}
