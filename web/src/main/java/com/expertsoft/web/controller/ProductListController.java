package com.expertsoft.web.controller;

import com.expertsoft.web.form.CartItem;
import com.expertsoft.core.service.CartService;
import com.expertsoft.core.service.PhoneService;
import com.expertsoft.web.form.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @RequestMapping(value = {"/productList", "/"}, method = RequestMethod.GET)
    public ModelAndView phoneList() {
        return new ModelAndView("productList", "phoneList", phoneService.findAll());
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
            int quantity = cartItem.getQuantity();
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public @ResponseBody AjaxResponse handleMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        final AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.addError("cartItem", messageSource.getMessage("typeMismatch.quantity", null, null));
        return ajaxResponse;
    }
}