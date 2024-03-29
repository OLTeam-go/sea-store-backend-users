package com.seastore.usersmicroservices.core.auth.services;


import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterAdminContract;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterCustomerContract;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterMerchantContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class SignUpService {
    private final UserRepo userRepo;
    private final WalletRepo walletRepo;

    @Autowired
    public SignUpService(UserRepo userRepo, WalletRepo walletRepo) {
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
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
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            userRepo.create(findUser);
            Wallet newWallet = new Wallet(
                    UUID.randomUUID(),
                    findUser.getID(),
                    new BigDecimal(0),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            walletRepo.create(newWallet);
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
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            userRepo.create(findUser);
            Wallet newWallet = new Wallet(
                    UUID.randomUUID(),
                    findUser.getID(),
                    new BigDecimal(0),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            walletRepo.create(newWallet);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<User> registerAdmin(RegisterAdminContract registerAdminContract) {
        User findUser = null;
        HttpStatus status = null;
        String token = "admin";

        try {
            if (registerAdminContract.getToken() == null || !registerAdminContract.getToken().equals(token)) {
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
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            userRepo.create(findUser);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }
}
