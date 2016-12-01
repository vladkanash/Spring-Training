package com.expertsoft.core.dao;

import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.expertsoft.core.dao.JdbcOrderItemDao.class,
com.expertsoft.core.dao.JdbcPhoneDao.class})

public class JdbcOrderItemDaoTest {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void getItemsForOrder() throws Exception {

    }

    @Test
    public void saveOrderItem() throws Exception {
        Phone phone = new Phone();
        phone.setModel("Apple Iphone 6S");
        phone.setPrice(new BigDecimal(799.99));
        phone.setColor("Black");
        phoneDao.savePhone(phone);
        Phone savedPhone = phoneDao.findAll().get(0);

        OrderItem testItem = new OrderItem();
        testItem.setQuantity(13);
        testItem.setPhone(savedPhone);

        orderItemDao.saveOrderItem(testItem);
        OrderItem item = orderItemDao.findAll().get(0);
        assertEquals(item.getQuantity(), testItem.getQuantity());
    }

    @Test
    public void getOrderItem() throws Exception {

    }

}