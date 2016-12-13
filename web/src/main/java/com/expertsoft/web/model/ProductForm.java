package com.expertsoft.web.model;

public class ProductForm {

    private long productKey;
    private int quantity;

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductForm that = (ProductForm) o;
        return productKey == that.productKey && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (productKey ^ (productKey >>> 32));
        result = 31 * result + quantity;
        return result;
    }
}
