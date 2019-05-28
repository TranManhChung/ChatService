package com.vng.chatservice.grpc;

import com.vng.chatservice.WebSocketServiceGrpc;
import com.vng.chatservice.WebSocketServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class WebSocketService extends WebSocketServiceGrpc.WebSocketServiceImplBase {

    @Override
    public void getWebsocketInfo(WebSocketServiceOuterClass.Message request,
                                 StreamObserver<WebSocketServiceOuterClass.WebsocketInfo> responseObserver) {

        WebSocketServiceOuterClass.WebsocketInfo websocketInfo = WebSocketServiceOuterClass.WebsocketInfo.newBuilder()
                .setEndpoint("http://localhost:8080").setTopic("/topic").build();
        responseObserver.onNext(websocketInfo);
        responseObserver.onCompleted();
    }

}
