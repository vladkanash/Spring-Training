package com.expertsoft.web.controller;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@PropertySource("classpath:config/application.properties")
public class OrderController {

    private static final String VIEW_NAME = "orderInfo";

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value="/orderInfo", method= RequestMethod.GET)
    public String getCartSummary(Model model) {
        populateDefaultModel(model);
        model.addAttribute(new OrderForm());
        return VIEW_NAME;
    }

    @RequestMapping(value="/submitOrder", method=RequestMethod.POST)
    public String submitOrder(@ModelAttribute @Valid OrderForm orderForm,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            populateDefaultModel(model);
            return VIEW_NAME;
        } else {
            final Order order = orderService.getOrderFromCart();
            copyShippingInfo(order, orderForm);
            orderService.saveOrder(order);
            model.asMap().clear();
            return "redirect:/orderSummary/" + order.getKey();
        }
    }

    private void populateDefaultModel(Model model) {
        final Order order = orderService.getOrderFromCart();
        model.addAttribute(order);
        model.addAttribute("shippingPrice", order.getShippingPrice());
    }

    private void copyShippingInfo(final Order order, final OrderForm orderForm) {
        order.setFirstName(orderForm.getFirstName());
        order.setLastName(orderForm.getLastName());
        order.setDeliveryAddress(orderForm.getDeliveryAddress());
        order.setContactPhone(orderForm.getContactPhone());
    }
}
