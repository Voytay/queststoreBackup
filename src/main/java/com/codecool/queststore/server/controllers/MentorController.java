package com.codecool.queststore.server.controllers;

import com.codecool.queststore.entities.Mentor;
import com.codecool.queststore.mappers.MentorMapper;
import com.codecool.queststore.services.MentorManager;
import com.codecool.queststore.services.ServiceLayerException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MentorController implements HttpHandler {
    private final MentorManager mentorManager;
    private final MentorMapper mentorMapper;

    public MentorController() {
        this.mentorManager = new MentorManager();
        this.mentorMapper = new MentorMapper();
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
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, PUT, DELETE");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.sendResponseHeaders(204, -1);
        }

        if (requestMethod.equals("GET")) {
            try {
                List<Mentor> mentors = uriHasIdentifier ?
                        mentorManager.get(identifier) : mentorManager.getAll();
                response = mentorMapper.mapToJson(mentors);

                setResponseHeader(exchange, MimeType.JSON);
                exchange.sendResponseHeaders(200, response.length());
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        } else if (requestMethod.equals("POST")) {
            try {
                Map<String, String> classes = new HashMap<>();
                classes.put("2017.11", "checked");
                classes.put("2016.09", "checked");

                mentorManager.create("jkowalski", "Jan", "Kowalski", "jan.kowalski@cc.pl", "haslo", classes);

                setResponseHeader(exchange, MimeType.JSON);
                exchange.sendResponseHeaders(201, response.length());
            } catch (ServiceLayerException e) {
                response = e.getMessage();

                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
            }
        } else if (requestMethod.equals("PUT")) {
            try {
                if (uriHasIdentifier) {
                    String login = "jkowalski";
                    String firstName = "Janusz";
                    String lastName = "Kowalski";
                    String email = "janusz.kowalski@cc.pl";
                    String password = "haslo";

                    Map<String, String> classes = new HashMap<>();
                    classes.put("2016.09", "checked");

                    mentorManager.edit(login, firstName, lastName, email, password, classes);

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
                    mentorManager.remove(identifier);

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
