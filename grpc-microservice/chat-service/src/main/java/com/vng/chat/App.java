package com.vng.chat;

import com.vng.chat.service.DemoServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {

    public static void main(String [] args) throws IOException, InterruptedException {

//        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext(true).build();
//        DemoServiceGrpc.DemoServiceBlockingStub stub = DemoServiceGrpc.newBlockingStub(channel);
//        DemoServiceOuterClass.HelloMessageRequest request = DemoServiceOuterClass.HelloMessageRequest.newBuilder()
//                .setMess("Hello Server").build();
//        DemoServiceOuterClass.HelloMessageResponse response = stub.connect(request);
//        System.out.println(response.getMess());
//        channel.shutdownNow();

        Server server = ServerBuilder.forPort(8082)
                .addService(new DemoServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();

    }

}
