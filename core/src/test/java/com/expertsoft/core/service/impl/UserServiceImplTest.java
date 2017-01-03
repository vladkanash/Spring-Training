package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.model.User;
import com.expertsoft.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/core-config.xml"})
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUser() throws Exception {
        UserDao userDao = mock(UserDao.class);
        User user = new User();
        user.setEnabled(true);
        user.setPassword("pass");
        user.setUsername("name");

        when(userDao.getUser("anna")).thenReturn(user);

        UserService userService = new UserServiceImpl(userDao, null);
        assertEquals(userService.getUser("anna").getPassword(), "pass");
    }

    @Test
    public void addUser() throws Exception {
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        when(encoder.encode("1234")).thenReturn("4321");

        UserService userService = new UserServiceImpl(userDao,  encoder);

        userService.addUser("name", "1234");
        assertEquals(userService.getUser("name").getPassword(), "4321");
    }
}