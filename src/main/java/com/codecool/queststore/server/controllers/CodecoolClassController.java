package com.codecool.queststore.server.controllers;

import com.codecool.queststore.entities.CodecoolClass;
import com.codecool.queststore.mappers.CodecoolClassMapper;
import com.codecool.queststore.services.CodecoolClassManager;
import com.codecool.queststore.services.ServiceLayerException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

public class CodecoolClassController implements HttpHandler {
    private final CodecoolClassManager codecoolClassManager;
    private final CodecoolClassMapper codecoolClassMapper;

    public CodecoolClassController() {
        this.codecoolClassManager = new CodecoolClassManager();
        this.codecoolClassMapper = new CodecoolClassMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        String requestMethod = exchange.getRequestMethod();

        URI uri = exchange.getRequestURI();
        boolean uriHasIdentifier = URIparser.hasIdentifier(uri);
        int identifier = uriHasIdentifier ? URIparser.parseIdentifierToInt(uri) : -1;

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, PUT, DELETE");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.sendResponseHeaders(204, -1);
        }

        if (requestMethod.equals("GET")) {
            try {
                List<CodecoolClass> classes = uriHasIdentifier ?
                        codecoolClassManager.get(identifier) : codecoolClassManager.getAll();
                response = codecoolClassMapper.mapToJson(classes);

                setResponseHeader(exchange, MimeType.JSON);
                exchange.sendResponseHeaders(200, response.length());
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        } else if (requestMethod.equals("POST")) {
            try {
                codecoolClassManager.create("2018.06");

                exchange.sendResponseHeaders(201, response.length());
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        } else if (requestMethod.equals("PUT")) {
            try {
                if (uriHasIdentifier) {
                    int id = 4;
                    String name = "2018.07";

                    codecoolClassManager.edit(id, name);

                    exchange.sendResponseHeaders(201, response.length());
                } else {
                    throw new ServiceLayerException("Request invalid: no identifier");
                }
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        } else if (requestMethod.equals("DELETE")) {
            try {
                if (uriHasIdentifier) {
                    codecoolClassManager.remove(identifier);

                    exchange.sendResponseHeaders(204, response.length());
                } else {
                    throw new ServiceLayerException("Request invalid: no identifier");
                }
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
