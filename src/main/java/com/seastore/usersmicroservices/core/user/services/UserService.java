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
        return userRepo.create(new User(
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
                )
        );
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public ResponseEntity<User> getByID(UUID ID) {
        User user = null;
        HttpStatus status = HttpStatus.OK;
        try {
            user = userRepo.getByID(ID);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<User>(user, status);
    }

    public ResponseEntity<Object> updateByID(UUID ID, User userToUpdate) {

        Integer rowChanges = userRepo.updateByID(ID, userToUpdate);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

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
        User user = null;
        HttpStatus status = HttpStatus.OK;
        try {
            user = userRepo.getByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<User>(user, status);
    }

    public ResponseEntity<Object> updateByUsername(String username, User userToUpdate) {
        Integer rowChanges = userRepo.updateByUsername(username, userToUpdate);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> updateByUsernameAndPassword(UUID ID, String username, String password, User userToUpdate) {
        Integer rowChanges = userRepo.updateByUsernameAndPassword(ID, username, password, userToUpdate);
        HttpStatus status = HttpStatus.OK;

        if (rowChanges == 0)
            status = HttpStatus.UNAUTHORIZED;

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
