package com.expertsoft.core.service;

import com.expertsoft.core.model.User;

import java.util.EnumSet;
import java.util.List;

public interface UserService {

    boolean addUser(String username, String password);

    User getUser(String username);

    List<User> findAll();

    EnumSet<User.Authority> getUserAuthorities(String username);
}
