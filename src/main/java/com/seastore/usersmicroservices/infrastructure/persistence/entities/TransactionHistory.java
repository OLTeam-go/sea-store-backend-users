package com.seastore.usersmicroservices.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class TransactionHistory {
    private final UUID ID;
    private final UUID userID;
    private final UUID walletID;
    private final BigDecimal amount;
    private final String type;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public TransactionHistory(UUID ID, UUID userID, UUID walletID, BigDecimal amount, String type, Timestamp createdAt, Timestamp updatedAt) {
        this.ID = ID;
        this.userID = userID;
        this.walletID = walletID;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getID() {
        return ID;
    }

    @JsonProperty("user_id")
    public UUID getUserID() {
        return userID;
    }

    @JsonProperty("wallet_id")
    public UUID getWalletID() {
        return walletID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}

