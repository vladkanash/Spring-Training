package com.expertsoft.web.model;

import java.util.HashMap;
import java.util.Map;

public class AjaxResponse {

    public enum ValidationStatus {
        SUCCESS,
        ERROR
    }

    private ValidationStatus validationStatus;
    private Map<String, String> errors = new HashMap<>();

    public AjaxResponse() {}

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

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

        if (validationStatus != that.validationStatus) return false;
        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = validationStatus != null ? validationStatus.hashCode() : 0;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }
}
