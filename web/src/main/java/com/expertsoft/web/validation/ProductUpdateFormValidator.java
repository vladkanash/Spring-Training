package com.expertsoft.web.validation;

import com.expertsoft.web.model.ProductUpdateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Map;

@Component
public class ProductUpdateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductUpdateForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        int quantityValue;
        ProductUpdateForm productForm = (ProductUpdateForm) o;
        Map<Long, String> productMap = productForm.getProductMap();
        for (String quantity : productMap.values()) {
            try {
                quantityValue = Integer.valueOf(quantity);
            } catch (NumberFormatException nfe) {
                errors.rejectValue("productMap", "cartInfo.quantity.notString");
                return;
            }
            if (quantityValue <= 0) {
                errors.rejectValue("productMap", "cartInfo.quantity.null");
            }
        }
    }
}