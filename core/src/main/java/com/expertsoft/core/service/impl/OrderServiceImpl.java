package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.OrderDao;
import com.expertsoft.core.exception.OrderNotFoundException;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order getOrder(final long key) throws OrderNotFoundException {
        final Order order = orderDao.getOrder(key);
        if (null != order) {
            return order;
        } else {
            throw new OrderNotFoundException(key);
        }
    }

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }
}
