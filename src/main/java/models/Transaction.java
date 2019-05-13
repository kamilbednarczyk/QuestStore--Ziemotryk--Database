package models;

public class Transaction {
    private int transactionId;
    private String buyer;
    private String date;
    private String artifactName;

    public Transaction(String buyer, String date, String artifactName) {
        this.buyer = buyer;
        this.date = date;
        this.artifactName = artifactName;
    }

    public Transaction(int transactionId, String buyer, String date, String artifactName) {
        this(buyer, date, artifactName);
        this.transactionId = transactionId;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getDate() {
        return date;
    }

    public String getArtifactName() {
        return artifactName;
    }
}
