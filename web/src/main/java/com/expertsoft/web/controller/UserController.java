package com.expertsoft.web.controller;

import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final OrderService orderService;

    @Autowired
    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ModelAndView userPage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ModelAndView("/user", "orderList", orderService.getOrdersForUser(username));
    }
}
