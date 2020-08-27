package com.seastore.usersmicroservices.infrastructure.persistence.entities;

import java.util.Date;
import java.util.UUID;

public class User {
    private final UUID ID;
    private final String username;
    private final String email;
    private final String password;
    private final String name;
    private final String gender;
    private final Boolean active;
    private final Date createdAt;
    private final Date updatedAt;

    public User(UUID ID, String username, String email, String password, String name, String gender, Boolean active, Date createdAt, Date updatedAt) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Boolean getActive() {
        return active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}

