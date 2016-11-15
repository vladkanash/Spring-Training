package com.expertsoft.core.model;

public class OrderItem {

    public OrderItem() {

    }

    private int quantity;
    private Phone phone;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        return phone != null ? phone.equals(orderItem.phone) : orderItem.phone == null;

    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
