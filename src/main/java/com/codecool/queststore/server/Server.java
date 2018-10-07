package com.codecool.queststore.server;

import com.codecool.queststore.server.controllers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

        httpServer.createContext("/", new RootController());
        httpServer.createContext("/mentors", new MentorController());
        httpServer.createContext("/classes", new CodecoolClassController());
        httpServer.createContext("/levels", new LevelController());
        httpServer.createContext("/store", new StoreController());
        httpServer.createContext("/students", new StudentController());


        httpServer.start();
    }
}
