package com.expertsoft.web.controller;

import com.expertsoft.web.model.ProductForm;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductDetailsController {

    private final PhoneService phoneService;
    private final CartService cartService;

    @Autowired
    public ProductDetailsController(PhoneService phoneService, CartService cartService) {
        this.phoneService = phoneService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/phone/{phoneKey}", method = RequestMethod.GET)
    public String getPhone(@PathVariable long phoneKey, Model model) {
        model.addAttribute(phoneService.getPhone(phoneKey));
        model.addAttribute("productForm", new ProductForm());
        return "productDetails";
    }
}
