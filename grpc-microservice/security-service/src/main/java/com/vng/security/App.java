package com.vng.security;

import com.vng.security.service.AuthServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {

    public static void main(String [] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8083)
                .addService(new AuthServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();

    }

}
