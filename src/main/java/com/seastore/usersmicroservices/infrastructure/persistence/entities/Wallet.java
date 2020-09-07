package com.seastore.usersmicroservices.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Wallet {
    private final UUID ID;
    private final UUID userID;
    private final BigDecimal balance;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public Wallet(UUID ID, UUID userID, BigDecimal balance, Timestamp createdAt, Timestamp updatedAt) {
        this.ID = ID;
        this.userID = userID;
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
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

