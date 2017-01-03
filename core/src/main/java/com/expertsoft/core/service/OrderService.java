package com.expertsoft.core.service;

import com.expertsoft.core.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order getOrder(final long key);

    void saveOrder(final Order order);

    Order getOrderFromCart();

    void setShippingPrice(Order order);

    boolean deliveryOrder(long orderKey);
}
