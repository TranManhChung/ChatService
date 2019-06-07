package com.vng.authservice.mail;

import com.vng.authservice.model.User;
import com.vng.authservice.service.AuthServiceImpl;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

public class MailSenderTests {
    private String mail;
    private String token;
    private  int type;

    public MailSenderTests(String mail, String token, int type){
        this.mail = mail;
        this.token = token;
        this.type = type;
    }

    //create test data
    @Parameterized.Parameters
    public static Collection<Object[]> date() throws ParseException {
        AuthServiceImpl service = new AuthServiceImpl();
        User user = new User("An", "anvo.ht209@gmail.com", "123", "abcxyz", "",
                "Male", null, false);

        Object[][] data = new Object[][] {{"", "", null},
                {user.getEmail(), service.generateToken(user.getEmail(), user.getPassword()), 1}};

        return Arrays.asList(data);
    }

    //test function
    @Test
    public void sendMail() {
        boolean expected = true;


    }
}
