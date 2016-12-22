package com.expertsoft.core.model;

import java.math.BigDecimal;

public class Phone {

    public Phone() {
    }

    private long key;
    private String model;
    private String color;
    private BigDecimal price;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Phone phone = (Phone) o;
        return key == phone.key;
    }

    @Override
    public int hashCode() {
        return (int) (key ^ (key >>> 32));
    }
}
