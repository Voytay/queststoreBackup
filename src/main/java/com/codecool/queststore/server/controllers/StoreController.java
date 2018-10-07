package com.codecool.queststore.server.controllers;

import com.codecool.queststore.entities.Artifact;
import com.codecool.queststore.mappers.ArtifactMapper;
import com.codecool.queststore.repositories.PersistenceLayerException;
import com.codecool.queststore.services.ArtifactManager;
import com.codecool.queststore.services.ServiceLayerException;
import com.codecool.queststore.services.ShopManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

public class StoreController implements HttpHandler {

    private final ArtifactManager artifactManager;
    private final ArtifactMapper artifactMapper;
    private final ShopManager shopManager;

    public StoreController() {
        this.artifactManager = new ArtifactManager();
        this.artifactMapper = new ArtifactMapper();
        this.shopManager = new ShopManager();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        String requestMethod = exchange.getRequestMethod();

        URI uri = exchange.getRequestURI();
        boolean uriHasIdentifier = URIparser.hasIdentifier(uri);
        int identifier = uriHasIdentifier ? URIparser.parseIdentifierToInt(uri) : -1;

        boolean uriHasQueryString = URIparser.hasQueryString(uri);
        String queryString = uriHasQueryString ? URIparser.getQueryString(uri) : "";

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, PUT, DELETE");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.sendResponseHeaders(204, -1);
        }

        if(requestMethod.equalsIgnoreCase("GET")) {
            try {

                List<Artifact> artifacts = uriHasIdentifier ? artifactManager.get(identifier) : artifactManager.getAll();
                response = artifactMapper.mapToJson(artifacts);
                setResponseHeader(exchange, MimeType.JSON);
                exchange.sendResponseHeaders(200, response.length());

            } catch (ServiceLayerException e) {

                response = e.getMessage();
                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
                e.printStackTrace();
            }
        } else if (requestMethod.equalsIgnoreCase("POST")) {
            try {
                if(uriHasIdentifier) {
                    String login = "edobkowski"; // TODO get login corresponding to active sessionId from db
                    shopManager.buyArtifact(login, identifier);

                    exchange.sendResponseHeaders(204, response.length());
                } else {
                    throw new ServiceLayerException("Invalid request: no identifier");
                }

            } catch (ServiceLayerException e) {
                response = e.getMessage();
                setResponseHeader(exchange, MimeType.TEXT);
                exchange.sendResponseHeaders(500, response.length());
                e.printStackTrace();
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
