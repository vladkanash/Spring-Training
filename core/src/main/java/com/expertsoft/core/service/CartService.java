package com.expertsoft.core.service;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;

import java.util.List;

public interface CartService {

    void addProductToCart(long productKey, int quantity);

    void removeProduct(long productKey);

    void updateProduct(long productKey, int newQuantity);

    int getProductCount();

    List<OrderItem> getOrderItems();

    Order getOrder();

    double getTotalPrice();
}
