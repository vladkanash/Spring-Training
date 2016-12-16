package com.expertsoft.web.validation;

import com.expertsoft.web.model.OrderInfoForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderSubmitFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderInfoForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderInfoForm orderInfo = (OrderInfoForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "orderInfo.firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "orderInfo.lastName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryAddress", "orderInfo.deliveryAddress.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPhone", "orderInfo.contactPhone.empty");
    }
}
