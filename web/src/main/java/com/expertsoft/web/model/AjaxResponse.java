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
}
