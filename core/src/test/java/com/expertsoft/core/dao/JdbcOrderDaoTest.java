package com.expertsoft.core.dao;

import com.expertsoft.core.config.CoreConfiguration;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ CoreConfiguration.class })
@Transactional
public class JdbcOrderDaoTest {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void getOrder() throws Exception {
        Order order = new Order();

        order.setFirstName("John");
        order.setLastName("Smith");

        orderDao.saveOrder(order);
        Order savedOrder = orderDao.getOrder(order.getKey());
        assertEquals(order.getFirstName(), savedOrder.getFirstName());
    }

    @Test
    public void saveOrder() throws Exception {
        Order order = new Order();
        order.setFirstName("John");
        order.setLastName("Smith");
        order.setDeliveryAddress("Greenfield st. 32-45");
        order.setContactPhone("877-4562356");
        order.setTotalPrice(new BigDecimal(1290.99));

        List<Phone> phones = phoneDao.findAll();
        List<OrderItem> items = new ArrayList<>();
        for (Phone phone : phones) {
            OrderItem item = new OrderItem();
            item.setQuantity(2);
            item.setPhone(phone);
            items.add(item);
        }
        order.setOrderItems(items);

        orderDao.saveOrder(order);
        Order savedOrder = orderDao.getOrder(order.getKey());
        assertEquals(order.getOrderItems().size(), savedOrder.getOrderItems().size());
    }

    @Test
    public void findAll() throws Exception {
        final int n = 5;

        for (int i = 0; i < n; i++) {
            Order order = new Order();
            orderDao.saveOrder(order);
        }

        assertEquals(orderDao.findAll().size(), n);
    }

}