package com.vng.authservice;

import com.vng.authservice.service.AuthServiceImpl;
import org.junit.*;

public class TestAuthServiceImpl{

    @Test
    public void decodeToken(){

        String username = "user";
        String password = "user";

        String expected = "user";

        AuthServiceImpl authService = new AuthServiceImpl();
        String token = authService.generateToken(username, password);
        String actual = authService.decodeToken(token).getClaim("username").asString();
        Assert.assertEquals(expected, actual);
    }
}