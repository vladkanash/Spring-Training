package com.expertsoft.core.exception;

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
