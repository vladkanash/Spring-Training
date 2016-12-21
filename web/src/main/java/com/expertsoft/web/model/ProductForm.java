package com.expertsoft.web.model;

public class ProductForm {

    private long productKey;
    private String quantity;

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductForm that = (ProductForm) o;

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
