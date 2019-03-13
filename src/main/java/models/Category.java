package models;

public class Category {
    private int categoryId;
    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    Category(int categoryId, String categoryName) {
        this(categoryName);
        this.categoryId = categoryId;
    }

    // Getters & Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
