package com.expertsoft.web.controller;

import com.expertsoft.core.model.ProductForm;
import com.expertsoft.core.service.PhoneService;
import com.expertsoft.web.validation.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ProductListController {

    private final PhoneService phoneService;
    private final ProductFormValidator productFormValidator;

    @Autowired
    public ProductListController(PhoneService phoneService,
                                 ProductFormValidator productFormValidator) {
        this.phoneService = phoneService;
        this.productFormValidator = productFormValidator;
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

    @RequestMapping(value = "/productList", method = RequestMethod.POST)
    public String processAddToCart(@Validated @RequestBody ProductForm item, BindingResult result,
                                   Map<String, Object> model) {

        if (result.hasErrors()) {
            model.put("phoneList", phoneService.findAll());
            return "productList";
        }
        long key = item.getQuantity();
        return "redirect:productList";
    }


}