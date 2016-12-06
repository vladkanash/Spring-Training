package com.expertsoft.core.dao;

import com.expertsoft.core.config.DataSourceConfiguration;
import com.expertsoft.core.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ DataSourceConfiguration.class })
public class JdbcPhoneDaoTest {

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void savePhone() throws Exception {
        Phone testPhone = new Phone();
        testPhone.setModel("Samsung S4");
        testPhone.setColor("Black");
        testPhone.setPrice(new BigDecimal(659.99));

        phoneDao.savePhone(testPhone);
        String savedModel = phoneDao.getPhone(testPhone.getKey()).getModel();
        assertEquals(savedModel, testPhone.getModel());
    }

    @Test
    public void findAll() throws Exception {
        Phone phone1 = new Phone();
        phone1.setModel("Samsung A4");
        phone1.setColor("Black");
        phone1.setPrice(new BigDecimal(659.99));
        phone1.setKey(15L);

        Phone phone2 = new Phone();
        phone2.setModel("Apple IPhone");
        phone2.setColor("White");
        phone2.setPrice(new BigDecimal(990.00));
        phone2.setKey(16L);

        phoneDao.savePhone(phone1);
        phoneDao.savePhone(phone2);

        List<Phone> phones = phoneDao.findAll();
        assertTrue(phones.contains(phone1) && phones.contains(phone2));
    }
}