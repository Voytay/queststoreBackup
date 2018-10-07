package com.codecool.queststore.server.controllers;

public enum MimeType {
    JSON("application/json"),
    HTML("text/html"),
    TEXT("text/plain");

    private String typeCode;

    MimeType(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return this.typeCode;
    }
}
