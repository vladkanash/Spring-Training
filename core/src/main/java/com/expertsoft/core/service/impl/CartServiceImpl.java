package com.expertsoft.core.service.impl;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceImpl implements CartService {

    private BigDecimal totalPrice = new BigDecimal(0.0);
    private final Order order = new Order();
    private final PhoneService phoneService;
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Autowired
    public CartServiceImpl(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Override
    public void addProductToCart(long productKey, int quantity) {
        final Phone phone = phoneService.getPhone(productKey);
        if (null == phone) {
            return;
        }

        OrderItem item = new OrderItem();
        item.setPhone(phone);
        item.setQuantity(quantity);
        orderItems.add(item);

        repriceCart();
    }

    @Override
    public void removeProduct(long productKey) {
        for (final OrderItem item : orderItems) {
            if (productKey == item.getPhone().getKey()) {
                orderItems.remove(item);
                repriceCart();
                return;
            }
        }
    }

    @Override
    public void updateProduct(long productKey, int newQuantity) {
        if (newQuantity <= 0) {
            return;
        }

        for (final OrderItem item : orderItems) {
            if (productKey == item.getPhone().getKey()) {
                item.setQuantity(newQuantity);
                repriceCart();
                return;
            }
        }
    }

    @Override
    public int getProductCount() {
        int productCount = 0;
        for (final OrderItem item : orderItems) {
            productCount += item.getQuantity();
        }
        return productCount;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public Order getOrder() {
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice.doubleValue();
    }

    private void repriceCart() {
        totalPrice = new BigDecimal(0);
        for (final OrderItem item : orderItems) {
            final Phone phone = item.getPhone();
            BigDecimal itemPrice = phone.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }
    }
}
