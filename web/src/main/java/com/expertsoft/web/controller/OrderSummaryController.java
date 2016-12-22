package com.expertsoft.web.controller;

import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderSummaryController {

    private final static String VIEW_NAME = "orderSummary";

    private OrderService orderService;

    @Autowired
    public OrderSummaryController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value="/orderSummary/{orderKey}", method= RequestMethod.GET)
    public ModelAndView getOrderSummary(@PathVariable long orderKey) {
        return new ModelAndView(VIEW_NAME, "order", orderService.getOrder(orderKey));
    }
}
