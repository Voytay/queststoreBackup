package com.codecool.queststore.server.controllers;

import com.codecool.queststore.services.ServiceLayerException;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public static String getRequestBodyString(HttpExchange exchange) throws ServiceLayerException {
        try {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestBody = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }

            return requestBody.toString();
        } catch (IOException e) {
            throw new ServiceLayerException("Can't get request body: " + e.getMessage());
        }
    }

    public static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyAndValue = pair.split("=");
            String key = keyAndValue[0];
            String value = new URLDecoder().decode(keyAndValue[1], "UTF-8");
            map.put(key, value);
        }

        return map;
    }
}
