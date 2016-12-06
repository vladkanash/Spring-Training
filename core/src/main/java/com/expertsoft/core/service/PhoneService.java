package com.expertsoft.core.service;

import com.expertsoft.core.model.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> findAll();

    Phone getPhone(final long key);

    void savePhone(final Phone phone);
}
