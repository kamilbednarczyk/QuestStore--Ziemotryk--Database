package models;

public class Transaction {
    private int transactionId;
    private String buyer;
    private int date;
    private String artifactName;

    public Transaction(String buyer, int date, String artifactName) {
        this.buyer = buyer;
        this.date = date;
        this.artifactName = artifactName;
    }

    public Transaction(int transactionId, String buyer, int date, String artifactName) {
        this(buyer, date, artifactName);
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getBuyer() {
        return buyer;
    }

    public int getDate() {
        return date;
    }

    public String getArtifactName() {
        return getArtifactName();
    }
}
