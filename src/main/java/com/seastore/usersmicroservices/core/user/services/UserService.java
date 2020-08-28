package com.seastore.usersmicroservices.core.user.services;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
                        new Date(),
                        new Date()
                )
        );
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public User getById(UUID userId) {
        return userRepo.getByID(userId);
    }

    public Integer updateById(UUID userId, User userToUpdate) {
        return userRepo.updateByID(userId, userToUpdate);
    }

    public Integer deleteById(UUID userId) {
        return userRepo.deleteByID(userId);
    }

    public Integer deleteByUsername(String username) {
        return userRepo.deleteByUsername(username);
    }

    public Integer updateByUsername(String username, User userToUpdate) {
        return userRepo.updateByUsername(username, userToUpdate);
    }

    public ResponseEntity<Integer> updateByUsernameAndPassword(String username, User userToUpdate) {
        Integer result = userRepo.updateByUsernameAndPassword(username, userToUpdate);
        HttpStatus status = HttpStatus.OK;

        if (result == 0)
            status = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<Integer>(result, status);
    }

    public User getByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
