package com.vng.apigatewayservice.grpc;

import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.chatservice.WebSocketServiceGrpc;
import com.vng.chatservice.WebSocketServiceOuterClass;
import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;
    private static WebSocketServiceGrpc.WebSocketServiceBlockingStub webSocketServiceBlockingStub;

    static {
        init();
    }

    private static void init(){

        //final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8083").usePlaintext().build();
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8083").usePlaintext().build();
        authServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channel);
        channel = ManagedChannelBuilder.forTarget("localhost:8081").usePlaintext().build();
        webSocketServiceBlockingStub = WebSocketServiceGrpc.newBlockingStub(channel);
        //channel.shutdownNow();
    }

    public static AuthServiceOuterClass.Response login(String username, String password){

        AuthServiceOuterClass.LoginRequest request = AuthServiceOuterClass.LoginRequest.newBuilder()
                .setUsername(username).setPassword(password).build();
        AuthServiceOuterClass.Response response = authServiceBlockingStub.login(request);

        return response;
    }

    public static boolean checkToken(String token){

        AuthServiceOuterClass.Message request = AuthServiceOuterClass.Message.newBuilder().setMessage(token).build();
        AuthServiceOuterClass.Message response = authServiceBlockingStub.checkToken(request);

        return response.getMessage().equals("VALID_TOKEN");
    }

    public static WebSocketServiceOuterClass.WebsocketInfo getWebsocketInfo(){

        WebSocketServiceOuterClass.Message request = WebSocketServiceOuterClass.Message.newBuilder().build();
        WebSocketServiceOuterClass.WebsocketInfo response = webSocketServiceBlockingStub.getWebsocketInfo(request);

        return response;
    }

}
