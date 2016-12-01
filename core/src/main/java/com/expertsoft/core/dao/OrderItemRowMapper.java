package com.expertsoft.core.dao;

import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class OrderItemRowMapper implements RowMapper<OrderItem> {

    private final PhoneDao phoneDao;

    @Autowired
    public OrderItemRowMapper(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem item = new OrderItem();
        long phoneKey = resultSet.getLong(JdbcConstants.ORDER_ITEM_PHONE_COLUMN);
        Phone phone = phoneDao.getPhone(phoneKey);

        item.setKey(resultSet.getLong(JdbcConstants.ORDER_ITEM_KEY_COLUMN));
        item.setQuantity(resultSet.getInt(JdbcConstants.ORDER_ITEM_KEY_COLUMN));
        item.setPhone(phone);
        return item;
    }
}