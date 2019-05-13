package models;

import databaseAccess.BackpacksDAO;
import databaseAccess.ClassesDAO;

import java.util.ArrayList;
import java.util.List;

public class Codecooler {
    private int accountId;
    private int classId;
    private int backpackId;
    private String fullName;
    private String email;
    private String avatarFile;
    private int coolcoins;

    public Codecooler(int accountId, int classId, String fullName, String email, String avatarFile) {
        this.accountId = accountId;
        this.classId = classId;
        this.fullName = fullName;
        this.email = email;
        this.avatarFile = avatarFile;
        this.coolcoins = 0;
    }

    public Codecooler(String fullName, String email, String avatarFile, int coolcoins) {
        this.fullName = fullName;
        this.email = email;
        this.avatarFile = avatarFile;
        this.coolcoins = coolcoins;
    }

    public Codecooler(int accountId,
                      int classId,
                      int backpackId,
                      String fullName,
                      String email,
                      String avatarFile,
                      int coolcoins) {

        this(fullName, email, avatarFile, coolcoins);
        this.accountId = accountId;
        this.classId = classId;
        this.backpackId = backpackId;
    }

    // Getters & Setters
    public int getAccountId() {
        return accountId;
    }

    public int getClassId() {
        return classId;
    }

    public int getBackpackId() {
        return backpackId;
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

    public int getCoolcoins() {
        return coolcoins;
    }

    public void setCoolcoins(int coolcoins) {
        this.coolcoins = coolcoins;
    }

    public String getClassName() {
        return new ClassesDAO().get(classId).getClassName();
    }

    public List<Backpack> getBackpacks() {
        BackpacksDAO backpacksDAO = new BackpacksDAO();
        List<Backpack> allBackpacks = backpacksDAO.getAll();

        List<Backpack> codecoolerBackpacks = new ArrayList<>();

        for (int i = 0; i < allBackpacks.size(); i++) {
            if (allBackpacks.get(i).getBackpackId() == getBackpackId()) {
                codecoolerBackpacks.add(allBackpacks.get(i));
            }
        }

        return codecoolerBackpacks;
    }
}
