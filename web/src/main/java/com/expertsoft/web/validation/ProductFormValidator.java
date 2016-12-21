package com.expertsoft.web.validation;

import com.expertsoft.web.model.ProductForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "cartInfo.quantity.null");

        int quantity;
        ProductForm productForm = (ProductForm) o;
        try {
            quantity = Integer.valueOf(productForm.getQuantity());
        } catch (NumberFormatException nfe) {
            errors.rejectValue("quantity", "cartInfo.quantity.notString");
            return;
        }

        if (quantity <= 0) {
            errors.rejectValue("quantity", "cartInfo.quantity.null");
        }
    }
}
