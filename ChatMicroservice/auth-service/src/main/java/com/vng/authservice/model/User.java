package com.vng.authservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "chat_code")
    private String chatCode;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "isvalid")
    private boolean isValid;

    public User(String name, String email, String password, String chatCode, boolean isValid){
        this(name, email, password, "", chatCode, "", null, isValid);
    }

    public User(String name, String email, String password, String salt, String chatCode, String gender, Date birthday, boolean isValid){
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.chatCode = chatCode;
        this.gender = gender;
        this.birthday = birthday;
        this.isValid = isValid;
    }
}
