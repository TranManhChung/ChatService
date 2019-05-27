package com.vng.apigatewayservice.grpc.server;

import com.vng.apigateway.WebClientServiceGrpc;
import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.apigatewayservice.grpc.GrpcClient;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class WebClientServiceImpl extends WebClientServiceGrpc.WebClientServiceImplBase {



    @Override
    public void login(WebClientServiceOuterClass.LoginRequest request,
                      StreamObserver<WebClientServiceOuterClass.TokenResponse> responseObserver) {

        String token = GrpcClient.login(request.getUsername(), request.getPassword());

        WebClientServiceOuterClass.TokenResponse tokenResponse = WebClientServiceOuterClass.TokenResponse.newBuilder()
                .setToken(token).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void logout(WebClientServiceOuterClass.Message request,
                       StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {

    }

    @Override
    public void checkToken(WebClientServiceOuterClass.Message request,
                           StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {

    }

    @Override
    public void getWebsocketInfo(WebClientServiceOuterClass.Message request,
                                 StreamObserver<WebClientServiceOuterClass.WebsocketInfo> responseObserver) {

        String endpoint = "ERROR", topic = "";
        if(GrpcClient.checkToken(request.getMessage())){
            //temp data
            endpoint = "chung";
            topic = "chung";
        }

        WebClientServiceOuterClass.WebsocketInfo websocketInfo = WebClientServiceOuterClass.WebsocketInfo.newBuilder()
                .setEndpoint(endpoint).setTopic(topic).build();
        responseObserver.onNext(websocketInfo);
        responseObserver.onCompleted();

    }

}
