package com.seastore.usersmicroservices.infrastructure.delivery.converters;

public class RegisterOptionActiveContract {
    private final String username;

    public RegisterOptionActiveContract(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
