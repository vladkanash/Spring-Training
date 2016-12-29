package com.expertsoft.core.model;

import java.util.EnumSet;

public class User {

    public enum Authority {
        ROLE_ADMIN,
        ROLE_USER
    }

    public User() {}

    private String username;
    private String password;
    private EnumSet<Authority> authorities = EnumSet.noneOf(Authority.class);
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumSet<Authority> getAuthorities() {
        return authorities;
    }

    public void grantAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
