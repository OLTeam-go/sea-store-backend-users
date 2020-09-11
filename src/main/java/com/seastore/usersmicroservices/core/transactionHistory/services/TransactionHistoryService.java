package com.seastore.usersmicroservices.core.transactionHistory.services;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionHistoryService {

    private final TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    public TransactionHistoryService(TransactionHistoryRepo transactionHistoryRepo) {
        this.transactionHistoryRepo = transactionHistoryRepo;
    }

    public TransactionHistory create(TransactionHistory transactionHistory) {
        TransactionHistory createdTransactionHistory = new TransactionHistory(
                UUID.randomUUID(),
                transactionHistory.getUserID(),
                transactionHistory.getWalletID(),
                transactionHistory.getAmount(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        return transactionHistoryRepo.create(createdTransactionHistory);
    }

    public List<TransactionHistory> getAll() {
        return transactionHistoryRepo.getAll();
    }

    public ResponseEntity<TransactionHistory> getByID(UUID ID) {
        TransactionHistory findTransactionHistory = null;
        HttpStatus status = HttpStatus.OK;
        try {
            findTransactionHistory = transactionHistoryRepo.getByID(ID);
        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<TransactionHistory>(findTransactionHistory, status);
    }

    public ResponseEntity<Object> updateByID(UUID ID, TransactionHistory transactionHistoryToUpdate) {
        Integer rowChanges = 0;
        HttpStatus status = HttpStatus.OK;

        try {
            TransactionHistory findTransactionHistory = transactionHistoryRepo.getByID(ID);
            TransactionHistory updatedTransactionHistory = new TransactionHistory(
                    findTransactionHistory.getID(),
                    transactionHistoryToUpdate.getUserID(),
                    transactionHistoryToUpdate.getWalletID(),
                    transactionHistoryToUpdate.getAmount(),
                    transactionHistoryToUpdate.getCreatedAt(),
                    new Timestamp(System.currentTimeMillis())
            );
            rowChanges = transactionHistoryRepo.updateByID(ID, updatedTransactionHistory);

        } catch (EmptyResultDataAccessException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Object>(null, status);
    }

    public ResponseEntity<Object> deleteByID(UUID userID) {
        Integer rowChanges = transactionHistoryRepo.deleteByID(userID);
        HttpStatus status = HttpStatus.OK;
        if (rowChanges == 0)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<Object>(null, status);
    }
}
