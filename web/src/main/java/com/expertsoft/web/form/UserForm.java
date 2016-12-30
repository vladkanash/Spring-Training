package com.expertsoft.web.form;

import javax.validation.constraints.Size;

public class UserForm {

    public UserForm() {
    }

    @Size(min=1, max=30)
    private String username;

    @Size(min=6, max=20)
    private String password;

    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
