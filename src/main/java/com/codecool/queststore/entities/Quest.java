package com.codecool.queststore.entities;

public class Quest {
    private static final int NO_ID = -1;

    private int id;
    private String name;
    private String description;
    private int reward;

    public Quest(String name, String description, int reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.id = NO_ID;
    }

    public Quest(int id, String name, String description, int reward) {
        this(name, description, reward);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getReward() {
        return this.reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
