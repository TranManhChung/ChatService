package com.vng.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vng.authservice.google.GooglePojo;
import com.vng.authservice.google.GoogleUtils;
import com.vng.authservice.model.User;
import com.vng.authservice.repository.UserRepository;
import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@GRpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    private String issuer = "auth0";
    private String passphrase = "secret";
    private static int expireTime = 10 * 60 * 1000; //10 minute

    @Override
    public void login(AuthServiceOuterClass.Request request,
                      StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

        String token = "ERROR";
        String username = "ERROR";

        //check username and password
        Optional<User> user = userRepository.findByEmail(request.getUsername());

        if(user.isPresent()
                && user.get().isValid()
                && user.get().getPassword().equals(request.getPassword())){

            //generate token
            token = generateToken(request.getUsername(), request.getPassword());
            username = user.get().getName();
        }

        AuthServiceOuterClass.Response tokenResponse = AuthServiceOuterClass.Response.newBuilder()
                .setToken(AuthServiceOuterClass.Token.newBuilder().setToken(token).build()).setUsername(username).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void logout(AuthServiceOuterClass.Request request,
                       StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

    }

    @Override
    public void checkToken(AuthServiceOuterClass.Request request,
                           StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

        DecodedJWT jwt = decodeToken(request.getToken().getToken());
        AuthServiceOuterClass.Response response = null;

        if( jwt != null ){ //VALID_TOKE N
            User user = userRepository.findByEmail(jwt.getClaim("username").asString()).get();

            response = AuthServiceOuterClass.Response.newBuilder()
                    .setToken(AuthServiceOuterClass.Token.newBuilder()
                            .setStatus("VALID_TOKEN")
                            .build())
                    .setChatCode(
                            user.getChatCode()
                    ).setUsername(
                            user.getName()
                    ).build();

        }else { //INVALID_TOKEN

            response = AuthServiceOuterClass.Response.newBuilder()
                    .setToken(AuthServiceOuterClass.Token.newBuilder()
                            .setStatus("INVALID_TOKEN")
                            .build()).build();

        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void loginWithGoogle(AuthServiceOuterClass.Request request,
                                StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

        String accessToken;
        GooglePojo googlePojo = null;
        try {
            accessToken = googleUtils.getToken(request.getToken().getToken());
            googlePojo = googleUtils.getUserInfo(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!userRepository.findByEmail(googlePojo.getEmail()).isPresent()) {
            userRepository.save(new User(googlePojo.getEmail(), googlePojo.getEmail(), googlePojo.getEmail(), googlePojo.getEmail().split("@")[0], true));
        }
        //generate token
        String token = generateToken(googlePojo.getEmail(), googlePojo.getEmail());
        String username = googlePojo.getEmail();

        AuthServiceOuterClass.Response tokenResponse = AuthServiceOuterClass.Response.newBuilder()
                .setToken(AuthServiceOuterClass.Token.newBuilder().setToken(token).build()).setUsername(username).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void register(AuthServiceOuterClass.RegisterRequest request, StreamObserver<AuthServiceOuterClass.Message> responseObserver) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        String msg = "";
        Date date = null;

        //format to mysql date
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthday().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!user.isPresent()){
            User userToSave = new User();

            userToSave.setName(request.getFullname());
            userToSave.setPassword(request.getPassword());
            userToSave.setEmail(request.getEmail());
            userToSave.setGender(request.getGender());
            userToSave.setBirthday(date);
            userToSave.setChatCode(request.getFullname());
            userToSave.setValid(false);

            try {
                userRepository.save(userToSave);
                msg = "REGISTERED";

                new Thread(()->{
                    String token = generateToken(request.getEmail(), request.getPassword());

                    try {
                        sendHtmlTemplate(request.getEmail(),token, 1);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        AuthServiceOuterClass.Message response = AuthServiceOuterClass.Message
                .newBuilder()
                .setMessage(msg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void confirm(AuthServiceOuterClass.Message request, StreamObserver<AuthServiceOuterClass.Message> responseObserver) {
        DecodedJWT jwt = decodeToken(request.getMessage());
        String msg = "";

        if (jwt != null){
            String email = jwt.getClaim("username").asString();
            User user = userRepository.findByEmail(email).get();

            user.setValid(true);

            try {
                userRepository.save(user);
            } catch (Exception e){
                e.printStackTrace();
            }

            msg = "CONFIRM";
        }

        AuthServiceOuterClass.Message response = AuthServiceOuterClass.Message
                .newBuilder()
                .setMessage(msg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void forgot(AuthServiceOuterClass.ForgotRequest request, StreamObserver<AuthServiceOuterClass.Message> responseObserver) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        String msg = "";

        if (!user.isPresent()) {
            msg = "FAIL";
        } else {
            new Thread(()->{
                String token = generateToken(user.get().getEmail(), user.get().getPassword());

                try {
                    sendHtmlTemplate(request.getEmail(),token, 2);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();

            msg = "SUCCESS";
        }

        AuthServiceOuterClass.Message response = AuthServiceOuterClass.Message
                .newBuilder()
                .setMessage(msg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changePass(AuthServiceOuterClass.ChangeRequest request, StreamObserver<AuthServiceOuterClass.Message> responseObserver) {
        DecodedJWT jwt = decodeToken(request.getToken());
        String msg = "";

        if (jwt != null){
            String email = jwt.getClaim("username").asString();
            String newPass = request.getNewpass();

            User user = userRepository.findByEmail(email).get();

            user.setPassword(newPass);

            try {
                userRepository.save(user);
            } catch (Exception e){
                e.printStackTrace();
            }

            msg = "SUCCESS";
        } else {
            msg = "FAIL";
        }

        AuthServiceOuterClass.Message response = AuthServiceOuterClass.Message
                .newBuilder()
                .setMessage(msg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void sendHtmlTemplate(String mail, String token, int type) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean mutilpart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, mutilpart, "UTF-8");
        String html = "";

        if (type == 1){
            String link = "localhost:8086/confirmregister?token=" + token;
            html = "<div><a href='mailto:" + mail + "' target='_blank'>" + mail + "</a><br>" +
                    "\t\t\t\t\t\t\t\t- Your account: <a href='mailto:" + mail + "' target='_blank'> " + mail + "</a><br>\n" +
                    "\t\t\t\t\t\t\t\t- Please click <a href='http://" + link + "'>here</a> to confirm register for account <a href='mailto:" + mail + "' target='_blank'>" + mail + "</a>:<br>\n" +
                    "\t\t\t\t\t\t\t\t- If you don't request, please ignore this mail<br>\n" +
                    "\t\t\t\t\t\t\t\t<br><br>\n" +
                    "\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t\t\t- Don't reply this mail.<br>\n" +
                    "</div>";
        } else if (type == 2) {
            String link = "localhost:8086/confirmchangepassword?token=" + token;
            html = "<div><a href='mailto:" + mail + "' target='_blank'>" + mail + "</a><br>" +
                    "\t\t\t\t\t\t\t\t- Your account: <a href='mailto:" + mail + "' target='_blank'> " + mail + "</a><br>\n" +
                    "\t\t\t\t\t\t\t\t- Please click <a href='http://" + link + "'>here</a> to change your password<a href='mailto:" + mail + "' target='_blank'>" + mail + "</a>:<br>\n" +
                    "\t\t\t\t\t\t\t\t- If you don't request, please ignore this mail<br>\n" +
                    "\t\t\t\t\t\t\t\t<br><br>\n" +
                    "\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t\t\t- Don't reply this mail.<br>\n" +
                    "</div>";
        }

        message.setContent(html, "text/html");
        helper.setTo(mail);
        helper.setSubject("Confirm Register");

        emailSender.send(message);
    }

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

    public DecodedJWT decodeToken(String token) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(passphrase);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            System.out.println("The Token has expired");
        } catch (SignatureVerificationException e) {
            System.out.println("The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256");
        } catch (JWTVerificationException e){
            //Invalid signature/claims
            e.printStackTrace();
        }

        return jwt;
    }

}
