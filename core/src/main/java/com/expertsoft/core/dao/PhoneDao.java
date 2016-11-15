package com.expertsoft.core.dao;

import com.expertsoft.core.model.Phone;

import java.util.List;

public interface PhoneDao {

    Phone getPhone(long key);

    void savePhone(Phone phone);

    List<Phone> findAll();

    void close();
}
