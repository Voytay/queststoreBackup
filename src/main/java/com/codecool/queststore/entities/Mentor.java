package com.codecool.queststore.entities;

import java.util.List;
import java.util.ArrayList;

public class Mentor {
    private UserData userData;
    private List<CodecoolClass> classes;

    public Mentor(UserData userData) {
        this.userData = userData;
        this.classes = new ArrayList<>();
    }

    public Mentor(UserData userData, List<CodecoolClass> classes) {
        this.userData = userData;
        this.classes = classes;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<CodecoolClass> getClasses() {
        return this.classes;
    }

    public void setClasses(List<CodecoolClass> classes) {
        this.classes = classes;
    }

    public void addClass(CodecoolClass newClass) {
        this.classes.add(newClass);
    }

    public void removeClass(CodecoolClass classToRemove) {
        this.classes.remove(classToRemove);
    }
}
