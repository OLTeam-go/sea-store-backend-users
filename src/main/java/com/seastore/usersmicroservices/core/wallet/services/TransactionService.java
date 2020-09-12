package com.seastore.usersmicroservices.core.wallet.services;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.TransactionHistoryRepo;
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
public class TransactionService {

    private final WalletRepo walletRepo;
    private final TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    public TransactionService(WalletRepo walletRepo, TransactionHistoryRepo transactionHistoryRepo) {
        this.walletRepo = walletRepo;
        this.transactionHistoryRepo = transactionHistoryRepo;
    }


    public ResponseEntity<Object> debitByID(UUID ID, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByID(ID);
            if (findWallet.getBalance().subtract(amount).compareTo(new BigDecimal(0)) < 0) {
                status = HttpStatus.FORBIDDEN;
            } else {
                Wallet walletToUpdate = new Wallet(
                        findWallet.getID(),
                        findWallet.getUserID(),
                        findWallet.getBalance().subtract(amount),
                        findWallet.getCreatedAt(),
                        new Timestamp(System.currentTimeMillis())
                );
                rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);

                TransactionHistory transactionHistory = new TransactionHistory(
                        UUID.randomUUID(),
                        findWallet.getUserID(),
                        findWallet.getID(),
                        amount,
                        "debit",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                );
                transactionHistoryRepo.create(transactionHistory);
            }
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> creditByID(UUID ID, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByID(ID);
            Wallet walletToUpdate = new Wallet(
                    findWallet.getID(),
                    findWallet.getUserID(),
                    findWallet.getBalance().add(amount),
                    findWallet.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);

            TransactionHistory transactionHistory = new TransactionHistory(
                    UUID.randomUUID(),
                    findWallet.getUserID(),
                    findWallet.getID(),
                    amount,
                    "credit",
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            transactionHistoryRepo.create(transactionHistory);

        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }


    public ResponseEntity<Object> debitByUserID(UUID ID, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByUserID(ID);
            if (findWallet.getBalance().subtract(amount).compareTo(new BigDecimal(0)) < 0) {
                status = HttpStatus.FORBIDDEN;
            } else {
                Wallet walletToUpdate = new Wallet(
                        findWallet.getID(),
                        findWallet.getUserID(),
                        findWallet.getBalance().subtract(amount),
                        findWallet.getCreatedAt(),
                        new Timestamp(System.currentTimeMillis())
                );
                rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);
                TransactionHistory transactionHistory = new TransactionHistory(
                        UUID.randomUUID(),
                        findWallet.getUserID(),
                        findWallet.getID(),
                        amount,
                        "debit",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                );
                transactionHistoryRepo.create(transactionHistory);
            }
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> creditByUserID(UUID ID, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByUserID(ID);
            Wallet walletToUpdate = new Wallet(
                    findWallet.getID(),
                    findWallet.getUserID(),
                    findWallet.getBalance().add(amount),
                    findWallet.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);
            TransactionHistory transactionHistory = new TransactionHistory(
                    UUID.randomUUID(),
                    findWallet.getUserID(),
                    findWallet.getID(),
                    amount,
                    "credit",
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            transactionHistoryRepo.create(transactionHistory);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }


    public ResponseEntity<Object> debitByUserUsername(String username, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByUsername(username);
            if (findWallet.getBalance().subtract(amount).compareTo(new BigDecimal(0)) < 0) {
                status = HttpStatus.FORBIDDEN;
            } else {
                Wallet walletToUpdate = new Wallet(
                        findWallet.getID(),
                        findWallet.getUserID(),
                        findWallet.getBalance().subtract(amount),
                        findWallet.getCreatedAt(),
                        new Timestamp(System.currentTimeMillis())
                );
                rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);
                TransactionHistory transactionHistory = new TransactionHistory(
                        UUID.randomUUID(),
                        findWallet.getUserID(),
                        findWallet.getID(),
                        amount,
                        "debit",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                );
                transactionHistoryRepo.create(transactionHistory);
            }
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> creditByUserUsername(String username, BigDecimal amount) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            Wallet findWallet = walletRepo.getByUsername(username);
            Wallet walletToUpdate = new Wallet(
                    findWallet.getID(),
                    findWallet.getUserID(),
                    findWallet.getBalance().add(amount),
                    findWallet.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = walletRepo.updateByID(findWallet.getID(), walletToUpdate);
            TransactionHistory transactionHistory = new TransactionHistory(
                    UUID.randomUUID(),
                    findWallet.getUserID(),
                    findWallet.getID(),
                    amount,
                    "credit",
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            transactionHistoryRepo.create(transactionHistory);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }
}
