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
        long phoneKey = resultSet.getLong("PHONE_FK");
        Phone phone = phoneDao.getPhone(phoneKey);

        item.setKey(resultSet.getLong("KEY"));
        item.setQuantity(resultSet.getInt("QUANTITY"));
        item.setPhone(phone);
        return item;
    }
}