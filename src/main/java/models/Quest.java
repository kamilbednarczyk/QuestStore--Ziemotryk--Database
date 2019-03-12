package models;

public class Quest {
    private int questId;
    private int categoryId;
    private String name;
    private String description;
    private int coolcoinPrize;

    // Getters & Setters
    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

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

    public void setCoolcoinPrize(int coolcoinPrize) {
        this.coolcoinPrize = coolcoinPrize;
    }
}
