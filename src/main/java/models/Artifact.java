package models;

public class Artifact {
    private int artifactId;
    private String name;
    private String description;
    private int prize;

    public Artifact(String name, String description, int prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }

    // Getters & Setters
    public int getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(int artifactId) {
        this.artifactId = artifactId;
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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
