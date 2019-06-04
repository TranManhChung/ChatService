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

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@GRpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void login(AuthServiceOuterClass.Request request,
                      StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

        String token = "ERROR";
        String username = "ERROR";

        //check username and password
        Optional<User> user = userRepository.findByEmail(request.getUsername());
        if(user.isPresent()){
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

        if( jwt != null ){ //VALID_TOKE

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
            userRepository.save(new User(googlePojo.getEmail(), googlePojo.getEmail(), googlePojo.getEmail(), googlePojo.getEmail().split("@")[0]));
        }
        //generate token
        String token = generateToken(googlePojo.getEmail(), googlePojo.getEmail());
        String username = googlePojo.getEmail();

        AuthServiceOuterClass.Response tokenResponse = AuthServiceOuterClass.Response.newBuilder()
                .setToken(AuthServiceOuterClass.Token.newBuilder().setToken(token).build()).setUsername(username).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
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
