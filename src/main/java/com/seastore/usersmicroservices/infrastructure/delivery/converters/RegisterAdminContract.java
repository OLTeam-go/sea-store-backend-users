package com.seastore.usersmicroservices.infrastructure.delivery.converters;

public class RegisterAdminContract {
    private final String token;
    private final String username;
    private final String email;
    private final String password;
    private final String name;
    private final String gender;

    public RegisterAdminContract(String token, String username, String email, String password, String name, String gender) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public String getToken() {
        return token;
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
