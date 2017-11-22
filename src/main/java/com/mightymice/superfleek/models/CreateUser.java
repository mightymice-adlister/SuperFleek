package com.mightymice.superfleek.models;

import org.hibernate.validator.constraints.NotBlank;

public class CreateUser {
    @NotBlank(message ="You must enter a username")
    private String username;
    @NotBlank(message ="You must enter a password")
    private String password;
    private String confirmPassword;
    @NotBlank(message ="You must enter an email address")
    private String email;

    public CreateUser() {
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
