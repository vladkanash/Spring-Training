package com.expertsoft.web.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CartItem {

    @NotNull
    @Min(1)
    private Integer quantity;

    private long productKey;

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem that = (CartItem) o;

        if (productKey != that.productKey) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (productKey ^ (productKey >>> 32));
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
