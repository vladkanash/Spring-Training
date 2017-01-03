package com.expertsoft.core.dao.impl;

import com.expertsoft.core.dao.UserDao;
import com.expertsoft.core.dao.impl.UserExistsException;
import com.expertsoft.core.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/core-config.xml"})
@Transactional
public class JdbcUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUser() throws Exception {
        final User user1 = new User();
        final User user2 = new User();

        user1.setUsername("firstUser");
        user1.setPassword("pass1");
        user1.grantAuthority(User.Authority.ROLE_USER);
        user1.grantAuthority(User.Authority.ROLE_ADMIN);

        user2.setUsername("secondUser");
        user2.setPassword("pass2");
        user2.grantAuthority(User.Authority.ROLE_USER);

        userDao.saveUser(user1);
        userDao.saveUser(user2);

        assertTrue(userDao.getUser("firstUser").getAuthorities().contains(User.Authority.ROLE_ADMIN));
    }

    @Test
    public void saveUser() throws Exception {
        final User user = new User();
        user.setUsername("test");
        user.setPassword("pass");
        user.setEnabled(true);
        user.grantAuthority(User.Authority.ROLE_USER);

        userDao.saveUser(user);

        final User savedUser = userDao.getUser("test");
        assertEquals(user.getAuthorities(), savedUser.getAuthorities());
    }

    @Test
    public void findAll() throws Exception {
        final User user1 = new User();
        final User user2 = new User();
        final User user3 = new User();

        user1.setUsername("firstUser");
        user1.setPassword("pass1");

        user2.setUsername("user2");
        user2.setPassword("pass1");

        user3.setUsername("username");
        user3.setPassword("pass2");

        userDao.saveUser(user1);
        userDao.saveUser(user2);
        userDao.saveUser(user3);

        assertTrue(userDao.findAll().size() == 3);
    }

    @Test(expected = UserExistsException.class)
    public void existingUsername() throws Exception {
        final User user1 = new User();
        final User user2 = new User();

        user1.setUsername("username");
        user2.setUsername("username");
        user1.setPassword("pass1");
        user2.setPassword("pass2");

        userDao.saveUser(user1);
        userDao.saveUser(user2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyFields() throws Exception {
        final User user = new User();

        user.setUsername("user1");
        userDao.saveUser(user);
    }
}