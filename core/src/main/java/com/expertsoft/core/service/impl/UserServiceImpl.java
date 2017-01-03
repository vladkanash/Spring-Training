package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.dao.impl.UserExistsException;
import com.expertsoft.core.model.User;
import com.expertsoft.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.passwordEncoder = encoder;
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userDao.getUser(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public EnumSet<User.Authority> getUserAuthorities(String username) {
        return userDao.getUser(username).getAuthorities();
    }

    @Override
    public boolean addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.grantAuthority(User.Authority.ROLE_USER);

        try {
            userDao.saveUser(user);
        } catch (UserExistsException e) {
            return false;
        }
        return true;
    }
}
