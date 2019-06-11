package com.vng.uiwebapp.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String fullname;
    private String password;
    private String confirm;
    private String email;
    private String gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String birthday;

    public User(String fullname, String email, String password, String gender, String birthday) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }
}
