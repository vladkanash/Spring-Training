package com.expertsoft.core.dao;

import com.expertsoft.core.dao.impl.UserExistsException;
import com.expertsoft.core.model.User;

import java.util.List;

public interface UserDao {

    User getUser(String username);

    void saveUser(User user) throws UserExistsException;

    List<User> findAll();
}
