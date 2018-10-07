package com.codecool.queststore.entities;

public class Privilege {
    private static final int NO_ID = -1;
    private int id;
    private String name;

    public Privilege(String name) {
        this.id = NO_ID;
        this.name = name;
    }

    public Privilege(int id, String name) {
        this.id = id;
        this.name = name;
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
}
