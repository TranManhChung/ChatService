package com.vng.chat.service;

import com.vng.chat.DemoServiceGrpc;
import com.vng.chat.DemoServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class DemoServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {

    @Override
    public void connect(DemoServiceOuterClass.HelloMessageRequest request,
                        StreamObserver<DemoServiceOuterClass.HelloMessageResponse> responseObserver) {

        DemoServiceOuterClass.HelloMessageResponse response = DemoServiceOuterClass.HelloMessageResponse.newBuilder()
                .setMess("Hello Client").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
