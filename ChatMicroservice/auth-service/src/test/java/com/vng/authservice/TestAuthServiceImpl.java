package com.vng.authservice;

import com.vng.authservice.service.AuthServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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