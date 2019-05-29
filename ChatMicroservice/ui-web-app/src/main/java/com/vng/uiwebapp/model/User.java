package com.vng.uiwebapp.model;

import com.vng.apigateway.WebClientServiceOuterClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String fullname;
    private String username;
    private String password;
    private String confirm;
    private String email;
    private WebClientServiceOuterClass.RegisterRequest.Gender gender;
    private WebClientServiceOuterClass.Date date;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WebClientServiceOuterClass.RegisterRequest.Gender getGender() {
        return gender;
    }

    public void setGender(WebClientServiceOuterClass.RegisterRequest.Gender gender) {
        this.gender = gender;
    }

    public WebClientServiceOuterClass.Date getDate() {
        return date;
    }

    public void setDate(WebClientServiceOuterClass.Date date) {
        this.date = date;
    }
}
