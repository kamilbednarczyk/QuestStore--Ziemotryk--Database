package models;

public class Level {
    private int level;
    private int coolcoinsNeeded;
    private String levelName;
    private String levelDescription;

    public Level(int level,
                 int coolcoinsNeeded,
                 String levelName,
                 String levelDescription) {

        this.level = level;
        this.coolcoinsNeeded = coolcoinsNeeded;
        this.levelName = levelName;
        this.levelDescription = levelDescription;
    }

    // Getters & Setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCoolcoinsNeeded() {
        return coolcoinsNeeded;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLevelDescription() {
        return levelDescription;
    }
}
