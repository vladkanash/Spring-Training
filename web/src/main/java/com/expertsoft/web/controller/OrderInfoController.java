package com.expertsoft.web.controller;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.model.OrderInfoForm;
import com.expertsoft.web.validation.OrderSubmitFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

@Controller
@PropertySource("classpath:application.properties")
public class OrderInfoController {

    private static final String VIEW_NAME = "orderInfo";

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
        populateDefaultModel(model);
        model.addAttribute(new OrderInfoForm());
        return VIEW_NAME;
    }

    @RequestMapping(value="/submitOrder", method=RequestMethod.POST)
    public String submitOrder(@ModelAttribute @Validated OrderInfoForm orderInfoForm,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            populateDefaultModel(model);
            return VIEW_NAME;
        } else {
            final Order order = cartService.getOrder();
            copyShippingInfo(order, orderInfoForm);
            orderService.saveOrder(order);
            cartService.clear();
            model.asMap().clear();
            return "redirect:/orderSummary/" + order.getKey();
        }
    }

    private void populateDefaultModel(Model model) {
        final Order order = cartService.getOrder();
        model.addAttribute(order);
        model.addAttribute("shippingPrice", order.getShippingPrice());
    }

    private void copyShippingInfo(final Order order, final OrderInfoForm orderInfoForm) {
        order.setFirstName(orderInfoForm.getFirstName());
        order.setLastName(orderInfoForm.getLastName());
        order.setDeliveryAddress(orderInfoForm.getDeliveryAddress());
        order.setContactPhone(orderInfoForm.getContactPhone());
    }
}
