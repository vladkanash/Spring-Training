package com.expertsoft.core.service;

import com.expertsoft.core.model.User;

import java.util.EnumSet;
import java.util.List;

public interface UserService {

    User getUser(String username);

    boolean saveUser(User user);

    List<User> findAll();

    EnumSet<User.Authority> getUserAuthorities(String username);
}
