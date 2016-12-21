package com.expertsoft.web.validation;

import com.expertsoft.web.model.OrderInfoForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderSubmitFormValidator implements Validator {

    private final static String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";

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

        final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        final Matcher matcher = pattern.matcher(orderInfo.getContactPhone());
        if (!matcher.matches()) {
            errors.rejectValue("contactPhone", "orderInfo.contactPhone.invalid");
        }
    }
}
