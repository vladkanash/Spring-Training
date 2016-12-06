package com.expertsoft.core.dao;

import com.expertsoft.core.model.OrderItem;

import java.util.List;

public interface OrderItemDao {

    List<OrderItem> getItemsForOrder(long orderKey);

    List<OrderItem> findAll();

    void saveOrderItem(OrderItem item);

    OrderItem getOrderItem(long key);
}
