package com.expertsoft.web.controller;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.CartService;
import com.expertsoft.web.validation.OrderSubmitFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderSummaryController {

    private final CartService cartService;
    private final Validator orderSubmitFormValidator;


    @Autowired
    public OrderSummaryController(CartService cartService,
                                  OrderSubmitFormValidator validator) {
        this.cartService = cartService;
        this.orderSubmitFormValidator = validator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(orderSubmitFormValidator);
    }

    @RequestMapping(value="/orderSummary", method= RequestMethod.GET)
    public String getCartSummary(Model model) {
        model.addAttribute("productCount", cartService.getProductCount());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("order", cartService.getOrder());
        model.addAttribute("shippingPrice", 5);
        return "orderSummary";
    }

    @RequestMapping(value="/submitOrder", method=RequestMethod.POST)
    public String submitOrder(@ModelAttribute @Validated Order order,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("productCount", cartService.getProductCount());
            model.addAttribute("totalPrice", cartService.getTotalPrice());
            model.addAttribute("order", cartService.getOrder());
            model.addAttribute("shippingPrice", 5);
            return "/orderSummary";
        }
        return "redirect:/orderSummary";
    }
}
