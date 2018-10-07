package com.codecool.queststore.server.controllers;

import com.codecool.queststore.entities.Student;
import com.codecool.queststore.mappers.StudentMapper;
import com.codecool.queststore.services.ServiceLayerException;
import com.codecool.queststore.services.StudentManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

public class StudentController implements HttpHandler {

    private final StudentManager studentManager;
    private final StudentMapper studentMapper;

    public StudentController() {
        this.studentManager = new StudentManager();
        this.studentMapper = new StudentMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        String requestMethod = exchange.getRequestMethod();

        URI uri = exchange.getRequestURI();
        boolean uriHasIdentifier = URIparser.hasIdentifier(uri);
        String identifier = uriHasIdentifier ? URIparser.parseIdentifier(uri) : "";

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");


        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.sendResponseHeaders(204, -1);
        }

        if (requestMethod.equals("GET")) {
            try {

                List<Student> students = uriHasIdentifier ? studentManager.get(identifier) : studentManager.getAll();

                response = studentMapper.mapToJson(students);

                setResponseHeader(exchange, MimeType.JSON);
                exchange.sendResponseHeaders(200, response.length());
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        }

        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }

    private void setResponseHeader(HttpExchange exchange, MimeType type) {
        String mimeTypeCode = type.getTypeCode();

        exchange.getResponseHeaders().set("Content-Type", mimeTypeCode);
    }
}
