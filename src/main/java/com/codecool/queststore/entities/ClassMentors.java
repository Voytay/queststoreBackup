package com.codecool.queststore.entities;

public class ClassMentors {

    private int classId;
    private int mentorId;

    public ClassMentors(int classId, int mentorId) {
        this.classId = classId;
        this.mentorId = mentorId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}
