package com.vng.chatservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "chat_code")
    private String chatCode;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "isvalid")
    private boolean isValid;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
