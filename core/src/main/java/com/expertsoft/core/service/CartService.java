package com.expertsoft.core.service;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;

import java.util.List;

public interface CartService {

    void addProductToCart(final long productKey, int quantity);

    void removeProductFromCart(final long productKey);

    int getProductCount();

    List<OrderItem> getOrderItems();

    Order getOrder();

    double getTotalPrice();
}
