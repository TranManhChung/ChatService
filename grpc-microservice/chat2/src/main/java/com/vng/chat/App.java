package com.vng.chat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class App {

    public static void main(String [] args){

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext(true).build();
        DemoServiceGrpc.DemoServiceBlockingStub stub = DemoServiceGrpc.newBlockingStub(channel);
        DemoServiceOuterClass.HelloMessageRequest request = DemoServiceOuterClass.HelloMessageRequest.newBuilder()
                .setMess("Hello Server").build();
        DemoServiceOuterClass.HelloMessageResponse response = stub.connect(request);
        System.out.println(response.getMess());
        channel.shutdownNow();

    }

}
