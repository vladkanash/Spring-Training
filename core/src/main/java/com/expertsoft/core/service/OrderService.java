package com.expertsoft.core.service;


import com.expertsoft.core.service.impl.OrderNotFoundException;
import com.expertsoft.core.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order getOrder(final long key) throws OrderNotFoundException;

    void saveOrder(final Order order);
}
