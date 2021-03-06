package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.OrderDao;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CartService cartService;
    private final UserService userService;

    private final Environment env;

    @Autowired
    OrderServiceImpl(OrderDao orderDao,
                     CartService cartService,
                     Environment env,
                     UserService userService) {
        this.orderDao = orderDao;
        this.cartService = cartService;
        this.env = env;
        this.userService = userService;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order getOrder(final long key) {
        return orderDao.getOrder(key);
    }

    @Override
    public void saveOrder(Order order) {
        if (null == order) {
            throw new IllegalArgumentException("Cannot save order: order is null");
        }

        order.setUser(userService.getLoggedInUser());
        orderDao.saveOrder(order);
        cartService.clear();
    }

    @Override
    public Order getOrderFromCart() {
        Order order = new Order();
        order.setOrderItems(cartService.getItems());
        order.setTotalPrice(cartService.getTotalPrice());
        order.setShippingPrice(new BigDecimal(env.getProperty("shipping.price")));
        return order;
    }

    @Override
    public void setShippingPrice(Order order) {
        if (null == order) {
            throw new IllegalArgumentException("Cannot set shipping price: Order is null");
        }
        order.setShippingPrice(new BigDecimal(env.getProperty("shipping.price")));
    }

    @Override
    public boolean deliveryOrder(long orderKey) {
        orderDao.setDeliveredState(orderKey);
        return true;
    }

    @Override
    public List<Order> getOrdersForUser(String username) {
        return orderDao.getOrdersForUser(username);
    }
}
