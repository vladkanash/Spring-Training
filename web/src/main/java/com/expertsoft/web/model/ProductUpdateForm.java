package com.expertsoft.web.model;

import java.util.HashMap;
import java.util.Map;

public class ProductUpdateForm {

    private Map<Long, String> productMap = new HashMap<>();

    public Map<Long, String> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Long, String> productMap) {
        this.productMap = productMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductUpdateForm that = (ProductUpdateForm) o;

        return productMap != null ? productMap.equals(that.productMap) : that.productMap == null;
    }

    @Override
    public int hashCode() {
        return productMap != null ? productMap.hashCode() : 0;
    }
}
