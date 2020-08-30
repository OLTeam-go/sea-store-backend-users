package com.seastore.usersmicroservices.infrastructure.persistence.repositories;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserRepo {
    User create(User user);

    List<User> getAll();

    User getByID(UUID ID);

    Integer updateByID(UUID ID, User userToUpdate);

    Integer deleteByID(UUID ID);

    User getByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    Integer updateByUsername(String username, User userToUpdate);

    Integer updateByUsernameAndPassword(UUID ID, String username, String password, User userToUpdate);

    Integer deleteByUsername(String username);

}
