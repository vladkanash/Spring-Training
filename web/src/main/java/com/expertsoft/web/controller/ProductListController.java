package com.expertsoft.web.controller;

import com.expertsoft.web.model.ProductForm;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import com.expertsoft.web.model.AjaxResponse;
import com.expertsoft.web.validation.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ProductListController {

    private final PhoneService phoneService;
    private final Validator productFormValidator;
    private final MessageSource messageSource;
    private final CartService cartService;

    @Autowired
    public ProductListController(PhoneService phoneService,
                                 ProductFormValidator productFormValidator,
                                 MessageSource messageSource,
                                 CartService cartService) {
        this.phoneService = phoneService;
        this.productFormValidator = productFormValidator;
        this.messageSource = messageSource;
        this.cartService = cartService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(productFormValidator);
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String phoneList(Map<String, Object> model) {
        model.put("phoneList", phoneService.findAll());
        model.put("productForm", new ProductForm());
        return "productList";
    }

    @RequestMapping(value="/addToCart", method = RequestMethod.POST)
    public @ResponseBody AjaxResponse submittedFromData(@RequestBody @Validated ProductForm productForm,
                                                        BindingResult result) {
        final AjaxResponse response = new AjaxResponse();
        if (result.hasErrors()) {
            response.setValidationStatus(AjaxResponse.ValidationStatus.ERROR);
            for (ObjectError error : result.getAllErrors()) {
                final String message =  messageSource.getMessage(error, null);
                response.addError(error.getObjectName(), message);
            }
        } else {
            cartService.addProductToCart(productForm.getProductKey(), productForm.getQuantity());
            response.setValidationStatus(AjaxResponse.ValidationStatus.SUCCESS);
        }
        return response;
    }
}