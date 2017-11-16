package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.PhoneDao;
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
        final OrderItem item = new OrderItem();
        final long phoneKey = resultSet.getLong(JdbcConstants.ORDER_ITEM_PHONE_COLUMN);
        final Phone phone = phoneDao.getPhone(phoneKey);

        item.setId(resultSet.getLong(JdbcConstants.ORDER_ITEM_ID_COLUMN));
        item.setQuantity(resultSet.getInt(JdbcConstants.ORDER_ITEM_QUANTITY_COLUMN));
        item.setPhone(phone);
        return item;
    }
}