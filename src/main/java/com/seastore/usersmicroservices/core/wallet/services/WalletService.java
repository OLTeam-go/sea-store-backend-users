package com.seastore.usersmicroservices.core.wallet.services;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.WalletRepo;
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
public class WalletService {

    private final WalletRepo walletRepo;

    @Autowired
    public WalletService(@Qualifier("Wallet") WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    public Wallet create(Wallet wallet) {
        Wallet newWallet = new Wallet(
                wallet.getID(),
                wallet.getUserID(),
                wallet.getBalance(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        return walletRepo.create(newWallet);
    }

    public List<Wallet> getAll() {
        return walletRepo.getAll();
    }

    public ResponseEntity<Wallet> getByID(UUID ID) {
        Wallet findWallet = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findWallet = walletRepo.getByID(ID);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Wallet>(findWallet, status);
    }

    public ResponseEntity<Wallet> getByUserID(UUID UserID) {
        Wallet findWallet = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findWallet = walletRepo.getByUserID(UserID);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Wallet>(findWallet, status);
    }

    public ResponseEntity<Wallet> getByUsername(String username) {
        Wallet findWallet = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findWallet = walletRepo.getByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Wallet>(findWallet, status);
    }


    public ResponseEntity<Object> updateByID(UUID ID, Wallet walletToUpdate) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByID(ID);
            Wallet updatedWallet = new Wallet(
                    findWallet.getID(),
                    walletToUpdate.getUserID(),
                    walletToUpdate.getBalance(),
                    findWallet.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = walletRepo.updateByID(ID, updatedWallet);

        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> deleteByID(UUID ID) {
        Integer rowChanges = walletRepo.deleteByID(ID);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<Object>(null, status);
    }

}
