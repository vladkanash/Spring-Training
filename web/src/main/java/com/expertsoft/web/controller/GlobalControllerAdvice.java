package com.expertsoft.web.controller;

import com.expertsoft.core.service.impl.OrderNotFoundException;
import com.expertsoft.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = {"com.expertsoft.web.controller"} )
public class GlobalControllerAdvice {

    private final CartService cartService;

    @Autowired
    public GlobalControllerAdvice(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute
    public void cartInfo(Model model) {
        model.addAttribute("productCount", cartService.getProductCount());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handleConflict(OrderNotFoundException ex) {
        return new ModelAndView("/error/orderNotFound", "orderKey", ex.getKey());
    }
}
