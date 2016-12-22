package com.expertsoft.core.model;

public class OrderItem {

    public OrderItem() {

    }

    private long key;
    private int quantity;
    private Phone phone;
    private Order order;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;
        return key == orderItem.key;
    }

    @Override
    public int hashCode() {
        return (int) (key ^ (key >>> 32));
    }
}
