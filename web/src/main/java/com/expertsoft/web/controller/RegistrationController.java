package com.expertsoft.web.controller;

import com.expertsoft.core.service.UserService;
import com.expertsoft.web.form.UserForm;
import com.expertsoft.web.validation.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserFormValidator userFormValidator;
    private AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationController(UserService userService,
                                  UserFormValidator userFormValidator,
                                  @Qualifier("springAuthenticationManager")
                                              AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userFormValidator = userFormValidator;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public ModelAndView getUserForm() {
        return new ModelAndView("/register", "userForm", new UserForm());
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@ModelAttribute @Valid UserForm userForm,
                               BindingResult result,
                               HttpServletRequest request) {
        userFormValidator.validate(userForm, result);
        if (result.hasErrors()) {
              return "/register";
        } else {
            boolean success = userService.addUser(userForm.getUsername(), userForm.getPassword());
            if (!success) {
                result.addError(new FieldError("userForm",
                        "username",
                        userForm.getUsername(),
                        false,
                        new String[]{"register.userExists"},
                        null,
                        null));
                return "/register";
            }
            authenticateUserAndSetSession(userForm.getUsername(), userForm.getPassword(), request);
        }
        return "redirect:/productList";
    }

    private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
