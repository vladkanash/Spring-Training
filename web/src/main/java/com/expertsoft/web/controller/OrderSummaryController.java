package com.expertsoft.web.controller;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderSummaryController {

    private final static String VIEW_NAME = "orderSummary";

    private OrderService orderService;

    @Autowired
    public OrderSummaryController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value="/orderSummary/{orderKey}", method= RequestMethod.GET)
    public String getOrderSummary(@PathVariable long orderKey, Model model) {
        final Order order = orderService.getOrder(orderKey);
        if (null != order) {
            model.addAttribute(order);
        }
        return VIEW_NAME;
    }
}
