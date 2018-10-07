package com.codecool.queststore.entities;

public class Role {
    private static final int NO_ID = -1;
    private int id;
    private String name;

    public Role(String name) {
        this.id = NO_ID;
        this.name = name;
    }

    public Role(int id, String name) {
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
