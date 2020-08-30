package com.seastore.usersmicroservices.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class User {
    private final UUID ID;
    private final String username;
    private final String email;
    private final String password;
    private final String name;
    private final String gender;
    private final String type;
    private final Boolean active;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public User(UUID ID, String username, String email, String password, String name, String gender, String type, Boolean active, Date createdAt, Date updatedAt) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
        this.ID = null;
        this.username = null;
        this.email = null;
        this.password = null;
        this.name = null;
        this.gender = null;
        this.type = null;
        this.active = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    public UUID getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getType() {
        return type;
    }

    public Boolean getActive() {
        return active;
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

