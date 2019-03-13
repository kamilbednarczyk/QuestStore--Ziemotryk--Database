package models;

public class Codecooler {
    private int accountId;
    private int classId;
    private int backpackId;
    private String fullName;
    private String email;
    private String avatarFile;
    private int coolcoins;


    // Getters & Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getBackpackId() {
        return backpackId;
    }

    public void setBackpackId(int backpackId) {
        this.backpackId = backpackId;
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

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public int getCoolcoins() {
        return coolcoins;
    }

    public void setCoolcoins(int coolcoins) {
        this.coolcoins = coolcoins;
    }
}
