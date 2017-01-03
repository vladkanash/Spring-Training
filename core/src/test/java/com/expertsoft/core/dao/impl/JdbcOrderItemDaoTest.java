package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.OrderItemDao;
import com.expertsoft.core.dao.PhoneDao;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/core-config.xml"})
@Transactional
public class JdbcOrderItemDaoTest {

    private Phone phone;

    @Before
    public void createPhone() {
        Phone phone = new Phone();
        phone.setModel("Apple Iphone 6S");
        phone.setPrice(new BigDecimal(799.99));
        phone.setColor("Black");

        phoneDao.savePhone(phone);
        this.phone = phoneDao.findAll().get(0);
    }

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void getItemsForOrder() throws Exception {

    }

    @Test
    public void saveOrderItem() throws Exception {
        OrderItem testItem = new OrderItem();
        testItem.setQuantity(13);
        testItem.setPhone(phone);
        orderItemDao.saveOrderItem(testItem);

        List<OrderItem> items = orderItemDao.findAll();
        assertTrue(items.contains(testItem));
    }

    @Test
    public void getOrderItem() throws Exception {
        OrderItem testItem = new OrderItem();
        testItem.setQuantity(13);
        testItem.setPhone(phone);

        orderItemDao.saveOrderItem(testItem);
        OrderItem item = orderItemDao.getOrderItem(testItem.getKey());
        assertEquals(item.getPhone(), testItem.getPhone());
    }

    @Test
    public void findAll() throws Exception {
        OrderItem testItem = new OrderItem();
        testItem.setQuantity(7);
        testItem.setPhone(phone);

        int n = 7;
        for (int i = 0; i < n; i++) {
            orderItemDao.saveOrderItem(testItem);
        }
        List<OrderItem> items = orderItemDao.findAll();
        assertTrue(items.size() == n);
    }
}