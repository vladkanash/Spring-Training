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
@Secured({"ROLE_USER"})
public class UserController {

    private final OrderService orderService;

    @Autowired
    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable String username) {
        return new ModelAndView("/user", "orderList", orderService.getOrdersForUser(username));
    }

}
