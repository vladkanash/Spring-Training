package com.expertsoft.web.controller;

import com.expertsoft.core.model.Phone;
import com.expertsoft.web.form.CartItem;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import com.expertsoft.web.form.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductListController {

    private final PhoneService phoneService;
    private final MessageSource messageSource;
    private final CartService cartService;

    @Autowired
    public ProductListController(PhoneService phoneService,
                                 MessageSource messageSource,
                                 CartService cartService) {
        this.phoneService = phoneService;
        this.messageSource = messageSource;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public List<Phone> phoneList() {
        return phoneService.findAll();
    }

    @RequestMapping(value="/addToCart", method = RequestMethod.POST)
    public @ResponseBody AjaxResponse submittedFromData(@RequestBody @Valid CartItem cartItem,
                                                        BindingResult result,
                                                        HttpServletResponse response) {
        final AjaxResponse ajaxResponse = new AjaxResponse();
        if (result.hasErrors()) {
            copyErrors(result, ajaxResponse);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            int quantity = Integer.valueOf(cartItem.getQuantity());
            cartService.addProductToCart(cartItem.getProductKey(), quantity);
        }
        return ajaxResponse;
    }

    private void copyErrors(BindingResult result, AjaxResponse ajaxResponse) {
        for (ObjectError error : result.getAllErrors()) {
            final String message =  messageSource.getMessage(error, null);
            ajaxResponse.addError(error.getObjectName(), message);
        }
    }
}