package models;

import databaseAccess.CodecoolersDAO;
import databaseAccess.MentorsDAO;

import java.util.List;

public class Class {
    private int classId;
    private String className;

    public Class(String className) {
        this.className = className;
    }

    public Class(int classId, String className) {
        this(className);
        this.classId = classId;
    }

    // Getters & Setters
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAssignedMentor() {
        List<Mentor> mentorList = new MentorsDAO().getAll();

        for (Mentor mentor : mentorList) {
            if (mentor.getClassId() == classId) {
                return mentor.getFullName();
            }
        }

        return "No assigned mentor";
    }

    public int getNumberOfStudents() {
        List<Codecooler> codecoolerList = new CodecoolersDAO().getAll();
        int counter = 0;

        for (Codecooler codecooler : codecoolerList) {
            if (codecooler.getClassId() == classId) {
                counter++;
            }
        }

        return counter;
    }
}
