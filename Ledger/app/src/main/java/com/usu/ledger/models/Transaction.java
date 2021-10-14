package com.usu.ledger.models;

public class Transaction {
    private double amount;
    private long timestamp;
    private String details;
    private String userId;

    public Transaction(){}

    public Transaction(double amount, long timestamp, String details, String userId) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.details = details;
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getUserId() {
        return userId;
    }
}
