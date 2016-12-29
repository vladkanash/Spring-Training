package com.expertsoft.core.dao.impl;

public class UserExistsException extends Exception {

    private final String username;

    UserExistsException(final String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
