package com.expertsoft.core.model;

import java.math.BigDecimal;

public class Phone {

    public Phone() {

    }

    private Long key;
    private String model;
    private String color;
    private BigDecimal price;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
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

        if (key != null ? !key.equals(phone.key) : phone.key != null) {
            return false;
        }
        if (model != null ? !model.equals(phone.model) : phone.model != null) {
            return false;
        }
        if (color != null ? !color.equals(phone.color) : phone.color != null) {
            return false;
        }
        return price != null ? price.equals(phone.price) : phone.price == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
