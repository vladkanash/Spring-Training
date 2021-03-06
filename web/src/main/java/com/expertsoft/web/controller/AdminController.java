package com.expertsoft.web.controller;

import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminController {

    private final OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        return new ModelAndView("/admin", "orderList", orderService.findAll());
    }

    @RequestMapping(path = "/setDeliveredState/{orderKey}", method = RequestMethod.POST)
    public String deliveryOrder(@PathVariable long orderKey) {
        orderService.deliveryOrder(orderKey);
        return "redirect:/admin";
    }
}
