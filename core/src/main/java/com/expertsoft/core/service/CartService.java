package com.expertsoft.core.service;

import com.expertsoft.core.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addProductToCart(long productKey, int quantity);

    void removeProduct(long productKey);

    void updateProduct(long productKey, int newQuantity);

    int getProductCount();

    List<OrderItem> getItems();

    BigDecimal getTotalPrice();

    Cart getCart();

    void clear();
}
