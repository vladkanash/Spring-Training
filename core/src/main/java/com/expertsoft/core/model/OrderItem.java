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

        if (key != orderItem.key) return false;
        if (quantity != orderItem.quantity) return false;
        if (phone != null ? !phone.equals(orderItem.phone) : orderItem.phone != null) return false;
        return order != null ? order.equals(orderItem.order) : orderItem.order == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (key ^ (key >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
