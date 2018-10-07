package com.codecool.queststore.entities;

public class Artifact {
    private static final int NO_ID = -1;

    private int id;
    private String name;
    private String description;
    private int price;

    public Artifact(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = NO_ID;
    }

    public Artifact(int id, String name, String description, int price) {
        this(name, description, price);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
