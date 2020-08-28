package com.seastore.usersmicroservices.infrastructure.persistence.repositories;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserRepo {
    User create(User user);

    User getByID(UUID ID);

    List<User> getAll();

    Integer updateByID(UUID ID, User userToUpdate);

    Integer deleteByID(UUID ID);

    Integer deleteByUsername(String username);

    Integer updateByUsername(String username, User userToUpdate);

    Integer updateByUsernameAndPassword(String username, User userToUpdate);

    //========================================================
    User getByUsernameAndPassword(String username, String password);

    User getByUsername(String username);
}
