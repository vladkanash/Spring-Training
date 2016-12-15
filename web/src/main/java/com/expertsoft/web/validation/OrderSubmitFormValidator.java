package com.expertsoft.web.validation;

import com.expertsoft.core.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderSubmitFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotNull.quantity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotNull.quantity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryAddress", "NotNull.quantity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPhone", "NotNull.quantity");
    }
}
