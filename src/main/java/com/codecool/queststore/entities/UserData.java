package com.codecool.queststore.entities;

public class UserData {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private int hashedPassword;

    public UserData(String login) {
        this.login = login;
    }

    public UserData(String login, String firstName, String lastName, String email, Role role, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.hashedPassword = password.hashCode();
    }

    public UserData(String login, String firstName, String lastName, String email, Role role, int hashedPassword) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(int hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setPassword(String password) {
        this.hashedPassword = password.hashCode();
    }
}
