package com.vng.uiwebapp.grpc;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

    //private TestEntityManager entityManager = new TestEntityManager();

    //start server after test
    private void generateData(String email, String pass){
        GrpcClient.register(new User("", email, pass, "", ""));
    }

    @Test
    public void confirmRegisterRequest_thenReturnTrue(){
        String email = "email@gmail.com";
        String pass = "123";

        generateData(email, pass);

        String token = generateToken(email, pass);
        WebClientServiceOuterClass.Message response = GrpcClient.confirm(token);

        Assert.assertEquals("CONFIRM", response.getMessage());
    }

    @Test
    public void confirmRegisterRequest_thenReturnFalse(){
        WebClientServiceOuterClass.Message response = GrpcClient.confirm("");
        Assert.assertEquals(response.getMessage(), "");
    }

    @Test(expected = NullPointerException.class)
    public void confirmRegisterRequest_withException() {
        WebClientServiceOuterClass.Message response = GrpcClient.confirm(null);
    }

    @Test
    public void forgotPassMethod_sendMail_andReturnSuccess() {
        String email  = "email@gmail.com";

        generateData(email, "");

        WebClientServiceOuterClass.Message response = GrpcClient.forgot(email);

        Assert.assertEquals("SUCCESS", response.getMessage());
    }


    @Test
    public void forgotPassMethod_sendMail_andReturnFail() {
        String email  = "xxx@gmail.com";
        WebClientServiceOuterClass.Message response = GrpcClient.forgot(email);

        Assert.assertEquals("FAIL", response.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void forgotPassMethod_sendMail_nullPointerException(){
        WebClientServiceOuterClass.Message response = GrpcClient.forgot(null);
    }

    @Test(expected = MessagingException.class)
    public void forgotPassMethod_sendMail_messagingException(){

    }

    @Test
    public void changePassMethod_andReturnSuccess() {
        String email = "email@gmai.com";
        String pass = "123";
        String token = generateToken(email, pass);

        generateData(email, pass);

        WebClientServiceOuterClass.Message response = GrpcClient.changePass(token, "xxx");

        Assert.assertEquals("SUCCESS", response.getMessage());
    }

    @Test
    public void changePassMethod_andReturnFail() {
        WebClientServiceOuterClass.Message response = GrpcClient.changePass("", "xxx");

        Assert.assertEquals("FAIL", response.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void changePassMethod_withNullPointerException() {
        WebClientServiceOuterClass.Message response = GrpcClient.changePass(null, null);
    }

    private String issuer = "auth0";
    private String passphrase = "secret";
    private static int expireTime = 10 * 60 * 1000; //10 minute

    public String generateToken(String username, String password) {

        Date exp = new Date(System.currentTimeMillis() + expireTime);
        String token = null;
        try {
            Algorithm algorithmHS = Algorithm.HMAC256(passphrase);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withExpiresAt(exp)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .sign(algorithmHS);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return token;
    }
}
