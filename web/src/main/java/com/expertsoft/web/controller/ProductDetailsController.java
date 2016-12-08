package com.expertsoft.web.controller;

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

    @Autowired
    public ProductDetailsController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/phone/{phoneKey}", method = RequestMethod.GET)
    public String getPhone(@PathVariable long phoneKey, Model model) {
        model.addAttribute(phoneService.getPhone(phoneKey));
        return "productDetails";
    }
}
