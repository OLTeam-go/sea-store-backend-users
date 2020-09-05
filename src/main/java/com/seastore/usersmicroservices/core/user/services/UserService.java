package com.seastore.usersmicroservices.core.user.services;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User user) {
        User createdUser = new User(
                UUID.randomUUID(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getGender(),
                user.getType(),
                user.getActive(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        return userRepo.create(createdUser);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public ResponseEntity<User> getByID(UUID ID) {
        User findUser = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findUser = userRepo.getByID(ID);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<Object> updateByID(UUID ID, User userToUpdate) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            User findUser = userRepo.getByID(ID);
            User updatedUser = new User(
                    findUser.getID(),
                    userToUpdate.getUsername(),
                    userToUpdate.getEmail(),
                    userToUpdate.getPassword(),
                    userToUpdate.getName(),
                    userToUpdate.getGender(),
                    userToUpdate.getType(),
                    userToUpdate.getActive(),
                    findUser.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = userRepo.updateByID(ID, updatedUser);

        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> deleteByID(UUID userID) {
        Integer rowChanges = userRepo.deleteByID(userID);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<User> getByUsername(String username) {
        User findUser = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findUser = userRepo.getByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<Object> updateByUsername(String username, User userToUpdate) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;
        try {
            User findUser = userRepo.getByUsername(username);
            User updatedUser = new User(
                    findUser.getID(),
                    userToUpdate.getUsername(),
                    userToUpdate.getEmail(),
                    userToUpdate.getPassword(),
                    userToUpdate.getName(),
                    userToUpdate.getGender(),
                    userToUpdate.getType(),
                    userToUpdate.getActive(),
                    findUser.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = userRepo.updateByID(findUser.getID(), updatedUser);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> updateByUsernameAndPassword(UUID ID, String username, String password, User userToUpdate) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            User findUser = userRepo.getByID(ID);
            User updatedUser = new User(
                    findUser.getID(),
                    userToUpdate.getUsername(),
                    userToUpdate.getEmail(),
                    userToUpdate.getPassword(),
                    userToUpdate.getName(),
                    userToUpdate.getGender(),
                    userToUpdate.getType(),
                    userToUpdate.getActive(),
                    findUser.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            if (findUser.getUsername().equals(username) && findUser.getPassword().equals(password)) {
                rowChanges = userRepo.updateByID(findUser.getID(), userToUpdate);
            } else {
                status = HttpStatus.UNAUTHORIZED;
            }

        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> deleteByUsername(String username) {
        Integer rowChanges = userRepo.deleteByUsername(username);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<Object>(null, status);
    }
}
