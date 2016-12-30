package com.expertsoft.core.service.impl;

import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.model.Phone;
import com.expertsoft.core.service.Cart;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
class CartServiceImpl implements CartService {

    private final Cart cart;
    private final PhoneService phoneService;

    @Autowired
    public CartServiceImpl(PhoneService phoneService, Cart cart) {
        this.phoneService = phoneService;
        this.cart = cart;
    }

    @Override
    public void addProductToCart(long productKey, int quantity) {
        final Phone phone = phoneService.getPhone(productKey);
        if (null == phone) {
            throw new IllegalArgumentException("Product value is null");
        }

        final List<OrderItem> items = cart.getItems();
        for (final OrderItem item : items) {
            if (item.getPhone().getKey() == productKey) {
                item.setQuantity(item.getQuantity() + quantity);
                repriceCart();
                return;
            }
        }

        OrderItem item = new OrderItem();
        item.setPhone(phone);
        item.setQuantity(quantity);
        items.add(item);
        repriceCart();
    }

    @Override
    public void removeProduct(long productKey) {
        final List<OrderItem> items = cart.getItems();
        for (Iterator<OrderItem> iter = items.iterator(); iter.hasNext();) {
            OrderItem item = iter.next();
            if (productKey == item.getPhone().getKey()) {
                iter.remove();
                repriceCart();
                return;
            }
        }
    }

    @Override
    public void updateProduct(long productKey, int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity for product is less than 1");
        }

        for (final OrderItem item : cart.getItems()) {
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
        for (final OrderItem item : cart.getItems()) {
            productCount += item.getQuantity();
        }
        return productCount;
    }

    @Override
    public void clear() {
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return cart.getTotalPrice();
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public List<OrderItem> getItems() {
        return cart.getItems();
    }

    private void repriceCart() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal itemPrice;
        Phone phone;
        for (final OrderItem item : cart.getItems()) {
            phone = item.getPhone();
            itemPrice = phone.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }
        cart.setTotalPrice(totalPrice);
    }
}
