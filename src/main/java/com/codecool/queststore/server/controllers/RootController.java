package com.codecool.queststore.server.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equals("GET")) {
            response = "<html><head><title>QuestStore API</title><body>Welcome to QuestStore API</body></html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
        }

        exchange.sendResponseHeaders(200, response.length());

        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();

//        String response = "Hello World!";
//        exchange.sendResponseHeaders(200, response.length());
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
    }
}
