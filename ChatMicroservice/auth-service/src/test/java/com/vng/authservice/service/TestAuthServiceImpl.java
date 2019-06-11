package com.vng.authservice.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.vng.authservice.service.AuthServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthServiceImpl{

    private static String username, password;
    private static AuthServiceImpl authService;

    @BeforeClass
    public static void init(){
        username = "user";
        password = "password";
        authService = new AuthServiceImpl();
    }

    @Test
    public void decodeToken_success(){

        String expected = "user";
        String token = authService.generateToken(username, password);
        String actual = authService.decodeToken(token).getClaim("username").asString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decodeToken_failure(){

        String expected = null;
        DecodedJWT actual = authService.decodeToken("part1.part2.part3");
        Assert.assertEquals(expected, actual);
    }


}