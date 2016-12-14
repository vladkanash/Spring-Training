package com.expertsoft.web.controller;

import com.expertsoft.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderSummaryController {

    private final CartService cartService;

    @Autowired
    public OrderSummaryController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value="/orderSummary", method= RequestMethod.GET)
    public String getCartSummary(Model model) {
        model.addAttribute("productCount", cartService.getProductCount());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("order", cartService.getOrder());
        model.addAttribute("shippingPrice", 5);
        return "orderSummary";
    }
}
