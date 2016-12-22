package com.expertsoft.web.form;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CartForm {

    public CartForm() {

    }

    @Valid
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
