package com.codecool.queststore.entities;

public class Admin {
    private UserData userData;

    public Admin(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
