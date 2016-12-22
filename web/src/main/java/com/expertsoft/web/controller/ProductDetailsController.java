package com.expertsoft.web.controller;

import com.expertsoft.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductDetailsController {

    private static final String VIEW_NAME = "productDetails";

    private final PhoneService phoneService;

    @Autowired
    public ProductDetailsController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/phone/{phoneKey}", method = RequestMethod.GET)
    public ModelAndView getPhone(@PathVariable long phoneKey) {
        return new ModelAndView(VIEW_NAME, "phone", phoneService.getPhone(phoneKey));
    }
}
