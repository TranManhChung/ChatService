package com.vng.uiwebapp.grpc;

import com.vng.apigateway.WebClientServiceGrpc;
import com.vng.apigateway.WebClientServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static WebClientServiceGrpc.WebClientServiceBlockingStub webClientServiceBlockingStub;

    static {
        init();
    }

    private static void init(){

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        webClientServiceBlockingStub = WebClientServiceGrpc.newBlockingStub(channel);
        //channel.shutdownNow();
    }

    public static String login(String username, String password){

        WebClientServiceOuterClass.LoginRequest request = WebClientServiceOuterClass.LoginRequest.newBuilder()
                .setUsername(username).setPassword(password).build();
        WebClientServiceOuterClass.TokenResponse response = webClientServiceBlockingStub.login(request);

        return response.getToken();
    }

    public static void logout(){}

    public static WebClientServiceOuterClass.WebsocketInfo getWebsocketInfo(String token){

        WebClientServiceOuterClass.Message request = WebClientServiceOuterClass.Message.newBuilder().
                setMessage(token).build();
        WebClientServiceOuterClass.WebsocketInfo response = webClientServiceBlockingStub.getWebsocketInfo(request);

        return response;
    }

}
