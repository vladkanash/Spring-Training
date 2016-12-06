package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.PhoneDao;
import com.expertsoft.core.model.Phone;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PhoneServiceImpl implements PhoneService {

    private final PhoneDao phoneDao;

    @Autowired
    public PhoneServiceImpl(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public final List<Phone> findAll() {
        return phoneDao.findAll();
    }

    @Override
    public Phone getPhone(final long key) {
        return phoneDao.getPhone(key);
    }

    @Override
    public void savePhone(final Phone phone) {
        phoneDao.savePhone(phone);
    }
}
