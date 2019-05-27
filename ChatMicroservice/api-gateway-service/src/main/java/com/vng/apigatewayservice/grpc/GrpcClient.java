package com.vng.apigatewayservice.grpc;

import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    static {
        init();
    }

    private static void init(){

        final ManagedChannel channel1 = ManagedChannelBuilder.forTarget("localhost:8083").usePlaintext().build();
        authServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channel1);
        //channel.shutdownNow();
    }

    public static String login(String username, String password){

        AuthServiceOuterClass.LoginRequest request = AuthServiceOuterClass.LoginRequest.newBuilder()
                .setUsername(username).setPassword(password).build();
        AuthServiceOuterClass.TokenResponse response = authServiceBlockingStub.login(request);

        return response.getToken();
    }

    public static boolean checkToken(String token){

        AuthServiceOuterClass.Message request = AuthServiceOuterClass.Message.newBuilder().setMessage(token).build();
        AuthServiceOuterClass.Message response = authServiceBlockingStub.checkToken(request);

        return response.getMessage().equals("VALID_TOKEN");
    }

}
