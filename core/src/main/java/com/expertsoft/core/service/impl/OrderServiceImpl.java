package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.OrderDao;
import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.Cart;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CartService cartService;

    @Value("${shipping.price}")
    private BigDecimal shippingPrice;

    @Autowired
    OrderServiceImpl(OrderDao orderDao,
                     CartService cartService) {
        this.orderDao = orderDao;
        this.cartService = cartService;
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
        orderDao.saveOrder(order);
        cartService.clear();
    }

    @Override
    public Order getOrderFromCart() {
        Order order = new Order();
        order.setOrderItems(cartService.getItems());
        order.setTotalPrice(cartService.getTotalPrice());
        order.setShippingPrice(shippingPrice);
        return order;
    }

    @Override
    public void setShippingPrice(Order order) {
        if (null == order) {
            throw new OrderException("Cannot set shipping price: Order is null");
        }
        order.setShippingPrice(shippingPrice);
    }
}
