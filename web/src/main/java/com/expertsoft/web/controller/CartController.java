package com.expertsoft.web.controller;

import com.expertsoft.core.model.OrderItem;
import com.expertsoft.core.service.CartService;
import com.expertsoft.web.form.CartForm;
import com.expertsoft.web.form.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private static final String VIEW_NAME = "/cartInfo";

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value="/cartInfo", method=RequestMethod.GET)
    public String getCartItems(Model model) {
        model.addAttribute("productList", cartService.getItems());
        model.addAttribute("cartForm", populateCartForm(cartService.getItems()));
        return VIEW_NAME;
    }

    @RequestMapping(value="/deleteProduct/{productKey}", method=RequestMethod.DELETE)
    public String deleteProduct(@PathVariable long productKey) {
        cartService.removeProduct(productKey);
        return "redirect:" + VIEW_NAME;
    }

    @RequestMapping(value="/updateProduct", method=RequestMethod.POST)
    public String updateProduct(@ModelAttribute("cartForm") @Valid CartForm cartForm,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productList", cartService.getItems());
            return VIEW_NAME;
        } else {
            for (final CartItem item : cartForm.getItems()) {
                int quantity = Integer.valueOf(item.getQuantity());
                cartService.updateProduct(item.getProductKey(), quantity);
            }
            return "redirect:" + VIEW_NAME;
        }
    }

    private CartForm populateCartForm(final List<OrderItem> items) {
        final CartForm form = new CartForm();
        final List<CartItem> formItems = new ArrayList<>();

        for (final OrderItem item : items) {
            final CartItem formItem = new CartItem();
            formItem.setProductKey(item.getPhone().getKey());
            formItem.setQuantity(String.valueOf(item.getQuantity()));
            formItems.add(formItem);
        }

        form.setItems(formItems);
        return form;
    }

}
