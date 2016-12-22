package com.expertsoft.core.service.impl;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(long orderKey) {
        super();
        this.key = orderKey;
    }

    private long key;

    public long getKey() {
        return key;
    }
}
