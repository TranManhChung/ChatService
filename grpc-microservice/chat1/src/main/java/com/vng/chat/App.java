package com.vng.chat;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.vng.chat.service.DemoServiceImpl;
import java.io.IOException;

public class App {

    public static void  main(String[]args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8080).addService(new DemoServiceImpl()).build();
        server.start();
        server.awaitTermination();
    }

}
