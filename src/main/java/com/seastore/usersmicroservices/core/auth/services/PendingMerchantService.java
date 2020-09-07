package com.seastore.usersmicroservices.core.auth.services;


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
public class PendingMerchantService {
    private final UserRepo userRepo;

    @Autowired
    public PendingMerchantService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<List<User>> getAll() {
        HttpStatus status = HttpStatus.OK;
        List<User> allPendingMerchant = userRepo.getAllPendingMerchant();
        return new ResponseEntity<List<User>>(allPendingMerchant, status);
    }

    public ResponseEntity<Object> accept(UUID ID) {
        User findUser = null, userToUpdate = null;
        HttpStatus status = null;
        Integer rowChanges = null;

        try {
            findUser = userRepo.getByID(ID);
            userToUpdate = new User(
                    findUser.getID(),
                    findUser.getUsername(),
                    findUser.getEmail(),
                    findUser.getPassword(),
                    findUser.getName(),
                    findUser.getGender(),
                    findUser.getType(),
                    true,
                    new Timestamp(System.currentTimeMillis()),
                    findUser.getCreatedAt()
            );

            rowChanges = userRepo.updateByID(ID, userToUpdate);
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> reject(UUID ID) {
        HttpStatus status = null;
        Integer rowChanges = null;

        rowChanges = userRepo.deleteByID(ID);
        status = HttpStatus.OK;

        if (rowChanges == 0) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }
}
