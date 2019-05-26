package com.vng.apigateway.grpc;

import com.vng.chat.DemoServiceGrpc;
import com.vng.chat.DemoServiceOuterClass;
import com.vng.security.AuthServiceGrpc;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static DemoServiceGrpc.DemoServiceBlockingStub demoServiceBlockingStub;
    private static AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    static {
        init();
    }

    private static void init(){

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8082").usePlaintext().build();
        demoServiceBlockingStub = DemoServiceGrpc.newBlockingStub(channel);
        final ManagedChannel channel1 = ManagedChannelBuilder.forTarget("localhost:8083").usePlaintext().build();
        authServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channel1);
        //channel.shutdownNow();
    }

    public static String connect(String name){

        DemoServiceOuterClass.HelloMessageRequest request = DemoServiceOuterClass.HelloMessageRequest.newBuilder()
                .setMess(name).build();
        DemoServiceOuterClass.HelloMessageResponse response = demoServiceBlockingStub.connect(request);

        return response.getMess();
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
