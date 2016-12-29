package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.dao.impl.UserExistsException;
import com.expertsoft.core.model.User;
import com.expertsoft.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public boolean saveUser(User user) {
        try {
            userDao.saveUser(user);
        } catch (UserExistsException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public EnumSet<User.Authority> getUserAuthorities(String username) {
        return userDao.getUser(username).getAuthorities();
    }
}
