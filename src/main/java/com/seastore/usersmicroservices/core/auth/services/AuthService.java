package com.seastore.usersmicroservices.core.auth.services;


import com.seastore.usersmicroservices.infrastructure.delivery.converters.LoginContract;
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
public class AuthService {
    private final UserRepo userRepo;

    @Autowired
    public AuthService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<User> login(LoginContract loginContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userRepo.getByUsernameAndPassword(
                    loginContract.getUsername(),
                    loginContract.getPassword());

            if (!findUser.getActive()) {
                findUser = null;
                status = HttpStatus.UNAUTHORIZED;
            } else {
                status = HttpStatus.OK;
            }
        } catch (EmptyResultDataAccessException e) {
            findUser = null;
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<User>(findUser, status);
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
            if (registerAdminContract.getToken() != null && registerAdminContract.getToken().equals(token)) {
                findUser = userRepo.getByUsernameAndPassword(
                        registerAdminContract.getUsername(),
                        registerAdminContract.getPassword());
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.UNAUTHORIZED;
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

    public ResponseEntity<Integer> setActiveByUsername(String username) {
        User findUser = null, updatedUser = null;
        HttpStatus status = null;
        Integer result = 0;

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
                    true,
                    new Date(),
                    findUser.getCreatedAt()
            );

            result = userRepo.updateByID(findUser.getID(), updatedUser);
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            findUser = null;
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Integer>(result, status);

    }
}
