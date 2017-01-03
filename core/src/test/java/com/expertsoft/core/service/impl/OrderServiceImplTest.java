package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.OrderDao;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
public class OrderServiceImplTest {

    private Order order;

    @Before
    public void createOrder() {
        this.order = new Order();

        OrderItem item = new OrderItem();
        Phone phone = new Phone();
        phone.setColor("Red");
        phone.setModel("Nokia 2700");
        phone.setPrice(new BigDecimal(70.0));

        item.setPhone(phone);
        item.setQuantity(4);

        order.setOrderItems(Collections.singletonList(item));
        order.setFirstName("John");
        order.setLastName("Smith");
        order.setDeliveryAddress("Evergreen st. 64");
        order.setContactPhone("877-345-622");
        order.setShippingPrice(new BigDecimal(5.0));
        order.setTotalPrice(new BigDecimal(70.0));
    }

    @Test
    public void findAll() throws Exception {
        OrderDao orderDao = mock(OrderDao.class);
        when(orderDao.findAll()).thenReturn(Collections.singletonList(order));

        OrderServiceImpl orderService = new OrderServiceImpl(orderDao, null, null);
        assertEquals(orderService.findAll().size(), 1);
    }

    @Test
    public void getOrder() throws Exception {
        OrderDao orderDao = mock(OrderDao.class);
        when(orderDao.getOrder(23)).thenReturn(order);

        OrderServiceImpl orderService = new OrderServiceImpl(orderDao, null, null);
        assertEquals(orderService.getOrder(23).getLastName(), "Smith");
    }
}