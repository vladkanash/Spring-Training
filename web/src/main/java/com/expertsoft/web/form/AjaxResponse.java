package com.expertsoft.web.form;

import java.util.HashMap;
import java.util.Map;

public class AjaxResponse {

    private Map<String, String> errors = new HashMap<>();

    public AjaxResponse() {}

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(final String field, final String message) {
        errors.put(field, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AjaxResponse that = (AjaxResponse) o;

        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        return errors != null ? errors.hashCode() : 0;
    }
}
