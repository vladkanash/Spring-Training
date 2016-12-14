package com.expertsoft.web.controller;

import com.expertsoft.core.service.CartService;
import com.expertsoft.web.model.ProductUpdateForm;
import com.expertsoft.web.validation.ProductUpdateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CartSummaryController {

    private final CartService cartService;
    private final Validator productUpdateFormValidator;

    @Autowired
    public CartSummaryController(CartService cartService, ProductUpdateFormValidator validator) {
        this.cartService = cartService;
        this.productUpdateFormValidator = validator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(productUpdateFormValidator);
    }

    @RequestMapping(value="/cartSummary", method=RequestMethod.GET)
    public String getCartSummary(Model model) {
        model.addAttribute("productCount", cartService.getProductCount());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("productList", cartService.getOrderItems());
        model.addAttribute("productUpdateForm", new ProductUpdateForm());
        return "cartSummary";
    }

    @RequestMapping(value="/deleteProduct/{productKey}", method=RequestMethod.GET)
    public String deleteProduct(@PathVariable long productKey, Model model) {
        cartService.removeProduct(productKey);
        return "redirect:/cartSummary";
    }

    @RequestMapping(value="/updateProduct", method=RequestMethod.POST)
    public String updateProduct(@ModelAttribute("productUpdateForm") @Validated ProductUpdateForm productUpdateForm,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            //TODO error handling
        } else {
            for (final Map.Entry<Long, Integer> entry : productUpdateForm.getProductMap().entrySet()) {
                cartService.updateProduct(entry.getKey(), entry.getValue());
            }
        }
        return "redirect:/cartSummary";
    }
}
