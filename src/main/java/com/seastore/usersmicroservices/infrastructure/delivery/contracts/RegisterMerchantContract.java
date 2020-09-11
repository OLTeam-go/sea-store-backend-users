package com.seastore.usersmicroservices.infrastructure.delivery.contracts;

import java.util.Date;

public class RegisterMerchantContract {
    private final String username;
    private final String email;
    private final String password;
    private final String name;
    private final String gender;

    public RegisterMerchantContract(String username, String email, String password, String name, String gender, Boolean active, Date createdAt, Date updatedAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
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

}
