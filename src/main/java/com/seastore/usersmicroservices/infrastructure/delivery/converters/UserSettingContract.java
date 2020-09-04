package com.seastore.usersmicroservices.infrastructure.delivery.converters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;

public class UserSettingContract {
    private final LoginContract loginCredentials;
    private final User userToUpdate;

    public UserSettingContract(LoginContract loginCredentials, User userToUpdate) {
        this.loginCredentials = loginCredentials;
        this.userToUpdate = userToUpdate;
    }

    @JsonProperty("login_credentials")
    public LoginContract getLoginCredentials() {
        return loginCredentials;
    }

    @JsonProperty("user_to_update")
    public User getUserToUpdate() {
        return userToUpdate;
    }
}
