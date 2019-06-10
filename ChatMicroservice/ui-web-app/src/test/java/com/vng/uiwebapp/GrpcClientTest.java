package com.vng.uiwebapp;


import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcClientTest {

    private static String email;
    private static String password;
    private static String validToken;

    @BeforeClass
    public static void init(){
        email = "email";
        password = "password";
        validToken = "";
    }

    @Test
    public void register_success(){
        WebClientServiceOuterClass.Message message = GrpcClient.register(new User("fullname",password,"confirm",email,"gender"
                ,"dd/MM/yyyy"));
        assertThat(message.getMessage()).isEqualTo("REGISTERED");
    }

    @Test
    public void register_emailExisted(){
        WebClientServiceOuterClass.Message message = GrpcClient.register(new User("fullname",password,"confirm",email,"gender"
                ,"dd/MM/yyyy"));
        assertThat(message.getMessage()).isEqualTo("");
    }

    @Test
    public void login_success(){
        //set isValid = true
        WebClientServiceOuterClass.Response response = GrpcClient.login(email, password);
        validToken = response.getToken();
        assertThat(validToken).isNotEqualTo("ERROR");
    }

    @Test
    public void login_fail(){
        WebClientServiceOuterClass.Response response = GrpcClient.login(email + "something", password);
        assertThat(response.getToken()).isEqualTo("ERROR");
    }

    @Test
    public void checkGoogleLogin_success(){
        //phai thanh cong luc dang nhap voi google moi vao cho nay dc
    }

    @Test
    public void isValidToken_true(){
        boolean validToken = GrpcClient.isValidToken(GrpcClient.login(email, password).getToken());
        assertThat(validToken).isEqualTo(true);
    }

    @Test
    public void isValidToken_false(){
        boolean validToken = GrpcClient.isValidToken(GrpcClient.login(email, password).getToken() + "something");
        assertThat(validToken).isEqualTo(false);
    }

    @Test
    public void confirm(){
        //k co tra ve response
    }

    @Test
    public void getWebsocketInfo_success(){
        WebClientServiceOuterClass.WebsocketInfo websocketInfo = GrpcClient.getWebsocketInfo(validToken);
        assertThat(websocketInfo.getEndpoint()).isEqualTo("http://localhost:8080/ws");
        assertThat(websocketInfo.getTopic()).isEqualTo("/topic");
    }

    @Test
    public void getWebsocketInfo_fail(){
        WebClientServiceOuterClass.WebsocketInfo websocketInfo = GrpcClient.getWebsocketInfo(validToken + "something");
        assertThat(websocketInfo.getEndpoint()).isNotEqualTo("http://localhost:8080/ws");
        assertThat(websocketInfo.getTopic()).isNotEqualTo("/topic");
    }

}
