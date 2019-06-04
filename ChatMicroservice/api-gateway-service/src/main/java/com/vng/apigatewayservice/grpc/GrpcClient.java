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

        AuthServiceOuterClass.Request request = AuthServiceOuterClass.Request.newBuilder()
                .setUsername(username).setPassword(password).build();
        AuthServiceOuterClass.Response response = authServiceBlockingStub.login(request);

        return response;
    }

    public static AuthServiceOuterClass.Response loginWithGoogle(String code){

        AuthServiceOuterClass.Request request = AuthServiceOuterClass.Request.newBuilder()
                .setToken(AuthServiceOuterClass.Token.newBuilder().setToken(code).build()).build();
        AuthServiceOuterClass.Response response = authServiceBlockingStub.loginWithGoogle(request);
        return response;
    }

    public static AuthServiceOuterClass.Response checkToken(String token){

        AuthServiceOuterClass.Request request = AuthServiceOuterClass.Request.newBuilder().setToken(
                AuthServiceOuterClass.Token.newBuilder().setToken(token).build()).build();
        AuthServiceOuterClass.Response response = authServiceBlockingStub.checkToken(request);

        return response;
    }

    public static WebSocketServiceOuterClass.Response getWebsocketInfo(String username, String chatCode){

        WebSocketServiceOuterClass.Request request = WebSocketServiceOuterClass.Request.newBuilder()
                .setUsername(username).setChatCode(chatCode).build();
        WebSocketServiceOuterClass.Response response = webSocketServiceBlockingStub.getWebsocketInfo(request);

        return response;
    }

}
