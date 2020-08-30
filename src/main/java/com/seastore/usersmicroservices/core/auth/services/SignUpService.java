package com.seastore.usersmicroservices.core.auth.services;


import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterAdminContract;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterCustomerContract;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterMerchantContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class SignUpService {
    private final UserRepo userRepo;

    @Autowired
    public SignUpService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<User> registerCustomer(RegisterCustomerContract registerCustomerContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userRepo.getByUsernameAndPassword(
                    registerCustomerContract.getUsername(),
                    registerCustomerContract.getPassword());
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            findUser = new User(UUID.randomUUID(),
                    registerCustomerContract.getUsername(),
                    registerCustomerContract.getEmail(),
                    registerCustomerContract.getPassword(),
                    registerCustomerContract.getName(),
                    registerCustomerContract.getGender(),
                    "customer",
                    true,
                    new Date(),
                    new Date());
            userRepo.create(findUser);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<User> registerMerchant(RegisterMerchantContract registerMerchantContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userRepo.getByUsernameAndPassword(
                    registerMerchantContract.getUsername(),
                    registerMerchantContract.getPassword());
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            findUser = new User(UUID.randomUUID(),
                    registerMerchantContract.getUsername(),
                    registerMerchantContract.getEmail(),
                    registerMerchantContract.getPassword(),
                    registerMerchantContract.getName(),
                    registerMerchantContract.getGender(),
                    "merchant",
                    false,
                    new Date(),
                    new Date());
            userRepo.create(findUser);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<User> registerAdmin(RegisterAdminContract registerAdminContract) {
        User findUser = null;
        HttpStatus status = null;
        String token = "admin";

        try {
            if (registerAdminContract.getToken() == null || registerAdminContract.getToken().equals(token)) {
                status = HttpStatus.UNAUTHORIZED;
            } else {
                findUser = userRepo.getByUsernameAndPassword(
                        registerAdminContract.getUsername(),
                        registerAdminContract.getPassword());
                status = HttpStatus.OK;
            }
        } catch (EmptyResultDataAccessException e) {
            findUser = new User(UUID.randomUUID(),
                    registerAdminContract.getUsername(),
                    registerAdminContract.getEmail(),
                    registerAdminContract.getPassword(),
                    registerAdminContract.getName(),
                    registerAdminContract.getGender(),
                    "admin",
                    true,
                    new Date(),
                    new Date());
            userRepo.create(findUser);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }
}
