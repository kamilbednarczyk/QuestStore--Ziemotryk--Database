package models;

public class Quest {
    private int questId;
    private String name;
    private String description;
    private int coolcoinPrize;

    public Quest(String name, String description, int coolcoinPrize) {
        this.name = name;
        this.description = description;
        this.coolcoinPrize = coolcoinPrize;
    }

    public Quest(int questId, String name, String description, int coolcoinPrize) {
        this(name, description, coolcoinPrize);
        this.questId = questId;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoolcoinPrize() {
        return coolcoinPrize;
    }
}
