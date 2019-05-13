package models;

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

    public String getClassName() {
        return className;
    }
}
