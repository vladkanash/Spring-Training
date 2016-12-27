package com.expertsoft.web.controller;

import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminController {

    private final OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("orderList", orderService.findAll());
        return "/admin";
    }

    @RequestMapping(path = "/deliveryOrder/{orderKey}", method = RequestMethod.POST)
    public String deliveryOrder(@PathVariable String orderKey) {
        return "redirect:/admin";
    }
}
