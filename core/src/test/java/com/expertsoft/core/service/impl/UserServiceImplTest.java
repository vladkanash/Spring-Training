package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.model.User;
import com.expertsoft.core.service.UserService;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

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

        UserDao userDao = mock(UserDao.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public User answer(InvocationOnMock invocationOnMock) throws Throwable {
                User user = (User) invocationOnMock.getArguments()[0];
                assertEquals(user.getPassword(), "4321");
                return null;
            }
        }).when(userDao).saveUser(Matchers.any(User.class));

        UserService userService = new UserServiceImpl(userDao,  encoder);
        userService.addUser("name", "1234");
    }
}