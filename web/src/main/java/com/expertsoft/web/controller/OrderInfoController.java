package com.expertsoft.web.controller;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.model.OrderInfoForm;
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
public class OrderInfoController {

    private final CartService cartService;
    private final OrderService orderService;
    private final Validator orderSubmitFormValidator;


    @Autowired
    public OrderInfoController(CartService cartService,
                               OrderService orderService,
                               OrderSubmitFormValidator validator) {
        this.cartService = cartService;
        this.orderSubmitFormValidator = validator;
        this.orderService = orderService;
    }

    @InitBinder("orderInfoForm")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(orderSubmitFormValidator);
    }

    @RequestMapping(value="/orderInfo", method= RequestMethod.GET)
    public String getCartSummary(Model model) {
        model.addAttribute("productCount", cartService.getProductCount());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("order", cartService.getOrder());
        model.addAttribute("orderInfoForm", new OrderInfoForm());
        model.addAttribute("shippingPrice", 5);
        return "orderInfo";
    }

    @RequestMapping(value="/submitOrder", method=RequestMethod.POST)
    public String submitOrder(@ModelAttribute @Validated OrderInfoForm orderInfoForm,
                              BindingResult result,
                              @ModelAttribute Order order,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("productCount", cartService.getProductCount());
            model.addAttribute("totalPrice", cartService.getTotalPrice());
            model.addAttribute("order", cartService.getOrder());
            model.addAttribute("shippingPrice", 5);
            return "/orderInfo";
        } else {
            copyShippingInfo(order, orderInfoForm);
            orderService.saveOrder(order);
        }
        return "redirect:/orderSummary";
    }

    private void copyShippingInfo(final Order order, final OrderInfoForm orderInfoForm) {
        order.setFirstName(orderInfoForm.getFirstName());
        order.setLastName(orderInfoForm.getLastName());
        order.setDeliveryAddress(orderInfoForm.getDeliveryAddress());
        order.setContactPhone(orderInfoForm.getContactPhone());
    }
}
