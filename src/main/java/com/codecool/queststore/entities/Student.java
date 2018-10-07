package com.codecool.queststore.entities;

public class Student {
    private UserData userData;
    private int experience;
    private Wallet wallet;
    private CodecoolClass codecoolClass;

    public Student(UserData userData, Wallet wallet) {
        this.userData = userData;
        this.experience = 0;
        this.wallet = wallet;
    }

    public Student(UserData userData, int experience, Wallet wallet) {
       this(userData, wallet);
       this.experience = experience;
    }

    public Student(UserData userData, Wallet wallet, CodecoolClass codecoolClass) {
        this(userData, wallet);
        this.codecoolClass = codecoolClass;
    }

    public Student(UserData userData, int experience, Wallet wallet, CodecoolClass codecoolClass) {
        this(userData, experience, wallet);
        this.codecoolClass = codecoolClass;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addExperience(int experienceToAdd) {
        this.experience += experienceToAdd;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public CodecoolClass getCodecoolClass() {
        return this.codecoolClass;
    }

    public void setCodecoolClass(CodecoolClass codecoolClass) {
        this.codecoolClass = codecoolClass;
    }
}
