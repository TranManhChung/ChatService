package com.vng.apigateway.controller;

import com.vng.chat.DemoServiceGrpc;
import com.vng.chat.DemoServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static DemoServiceGrpc.DemoServiceBlockingStub stub;

    static {
        init();
    }

    private static void init(){
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8082").usePlaintext().build();
        stub = DemoServiceGrpc.newBlockingStub(channel);
        //channel.shutdownNow();
    }

    @GetMapping("/")
    public String hello(){
        DemoServiceOuterClass.HelloMessageRequest request = DemoServiceOuterClass.HelloMessageRequest.newBuilder()
                .setMess("Hello Server").build();
        DemoServiceOuterClass.HelloMessageResponse response = stub.connect(request);
        return response.getMess();
    }
}
