package com.expertsoft.core.service.impl;

import com.expertsoft.core.dao.PhoneDao;
import com.expertsoft.core.model.Phone;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        final List<Phone> phones = phoneDao.findAll();
        return phones != null ? phones : Collections.<Phone>emptyList();
    }

    @Override
    public Phone getPhone(final long key) {
        return phoneDao.getPhone(key);
    }

    @Override
    public void savePhone(final Phone phone) {
        if (null == phone) {
            throw new IllegalArgumentException("Cannot save phone: phone value is null");
        }
        phoneDao.savePhone(phone);
    }
}
