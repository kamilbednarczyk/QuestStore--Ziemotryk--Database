package models;

import databaseAccess.ClassesDAO;

public class Mentor {
    private int accountId;
    private String fullName;
    private String email;
    private int classId;
    private String about;
    private String avatarFile;

    // Use for update
    public Mentor(String fullName, String email, int classId, String about, String avatarFile) {
        this.fullName = fullName;
        this.email = email;
        this.classId = classId;
        this.about = about;
        this.avatarFile = avatarFile;
    }

    public Mentor(int accountId, String fullName, String email, int classId, String about, String avatarFile) {
        this(fullName, email, classId, about, avatarFile);
        this.accountId = accountId;
    }

    // Getters & Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getClassName() {
        return new ClassesDAO().get(classId).getClassName();
    }

}
