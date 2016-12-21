package com.expertsoft.core.service.impl;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@PropertySource("classpath:application.properties")
class CartServiceImpl implements CartService {

    private BigDecimal totalPrice = BigDecimal.ZERO;
    private final PhoneService phoneService;
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Value("${shipping.price}")
    private BigDecimal shippingPrice;

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

        for (final OrderItem item : orderItems) {
            if (item.getPhone().getKey() == productKey) {
                item.setQuantity(item.getQuantity() + quantity);
                repriceCart();
                return;
            }
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
    public Order getOrder() {
        Order order = new Order();
        order.setShippingPrice(shippingPrice);
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public void clear() {
        orderItems.clear();
        totalPrice = BigDecimal.ZERO;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice.doubleValue();
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    private void repriceCart() {
        totalPrice = BigDecimal.ZERO;
        for (final OrderItem item : orderItems) {
            final Phone phone = item.getPhone();
            BigDecimal itemPrice = phone.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }
    }
}
