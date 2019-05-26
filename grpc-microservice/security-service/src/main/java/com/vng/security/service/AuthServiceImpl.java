package com.vng.security.service;

import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.stub.StreamObserver;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;


public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void login(AuthServiceOuterClass.LoginRequest request,
                      StreamObserver<AuthServiceOuterClass.TokenResponse> responseObserver) {

        String token = "ERROR";
        //check username and password
        if(request.getUsername().equals("user") && request.getPassword().equals("user")){
            //generate token
            token = generateToken(request.getUsername(), request.getPassword());
        }

        AuthServiceOuterClass.TokenResponse tokenResponse = AuthServiceOuterClass.TokenResponse.newBuilder()
                .setToken(token).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void logout(AuthServiceOuterClass.Message request,
                       StreamObserver<AuthServiceOuterClass.Message> responseObserver) {

    }

    @Override
    public void checkToken(AuthServiceOuterClass.Message request,
                           StreamObserver<AuthServiceOuterClass.Message> responseObserver) {

        String isValid = decodeToken(request.getMessage()) == null ? "INVALID_TOKEN" : "VALID_TOKEN";
        AuthServiceOuterClass.Message message = AuthServiceOuterClass.Message.newBuilder().setMessage(isValid).build();
        responseObserver.onNext(message);
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
