package com.vng.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
import com.vng.authservice.model.User;
import com.vng.authservice.repository.UserRepository;
import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

@GRpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void login(AuthServiceOuterClass.LoginRequest request,
                      StreamObserver<AuthServiceOuterClass.Response> responseObserver) {

        String token = "ERROR";
        AuthServiceOuterClass.Response tokenResponse = AuthServiceOuterClass.Response.newBuilder()
                .setToken(token).setUsername("ERROR").build();
        //check username and password
        Optional<User> user = userRepository.findByEmail(request.getUsername());
        if(user.isPresent()){
            //generate token
            token = generateToken(request.getUsername(), request.getPassword());
            tokenResponse = AuthServiceOuterClass.Response.newBuilder()
                    .setToken(token).setUsername(user.get().getName()).build();
        }

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

    @Override
    public void register(AuthServiceOuterClass.RegisterRequest request, StreamObserver<AuthServiceOuterClass.Message> responseObserver) {
        Optional<User> username = userRepository.findByUsername(request.getUsername());
        Optional<User> email = userRepository.findByEmail(request.getEmail());
        String msg = "";

        if (username != null && email != null){
            User user = new User();
            user.setUsername(request.getUsername());
            user.setName(request.getFullname());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setGender(null);
            user.setBirthday(null);
            user.setChatCode(null);

            userRepository.save(user);

            msg = "REGISTERED";
        }

        AuthServiceOuterClass.Message message = AuthServiceOuterClass.Message
                .newBuilder()
                .setMessage(msg)
                .build();

        responseObserver.onNext(message);
        responseObserver.onCompleted();
    }
}
