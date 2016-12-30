package com.expertsoft.web.validation;

import com.expertsoft.web.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm form = (UserForm) o;

        if (!form.getPassword().equals(form.getMatchingPassword())) {
            errors.rejectValue("matchingPassword", "register.password.notMatching");
        }
    }
}
