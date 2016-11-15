package com.expertsoft.core.dao;

import com.expertsoft.core.model.Order;

import java.util.List;

public interface OrderDao {

    Order getOrder(long key);

    void saveOrder(Order order);

    List<Order> findAll();

    void close();
}
