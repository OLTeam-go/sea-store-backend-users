package com.seastore.usersmicroservices.core.auth.services;


import com.seastore.usersmicroservices.infrastructure.delivery.converters.OptionActiveContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SettingService {
    private final UserRepo userRepo;

    @Autowired
    public SettingService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<Object> setActiveByUsername(String username, OptionActiveContract optionActiveContract) {
        User findUser = null, updatedUser = null;
        HttpStatus status = null;
        Integer rowChanges = null;

        try {
            findUser = userRepo.getByUsername(username);
            updatedUser = new User(
                    findUser.getID(),
                    findUser.getUsername(),
                    findUser.getEmail(),
                    findUser.getPassword(),
                    findUser.getName(),
                    findUser.getGender(),
                    findUser.getType(),
                    optionActiveContract.isActive(),
                    new Timestamp(System.currentTimeMillis()),
                    findUser.getCreatedAt()
            );

            rowChanges = userRepo.updateByID(findUser.getID(), updatedUser);
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }


}
