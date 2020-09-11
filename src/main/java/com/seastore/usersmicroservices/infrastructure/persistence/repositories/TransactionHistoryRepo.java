package com.seastore.usersmicroservices.infrastructure.persistence.repositories;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;

import java.util.List;
import java.util.UUID;

public interface TransactionHistoryRepo {
    TransactionHistory create(TransactionHistory transactionHistory);

    List<TransactionHistory> getAll();

    TransactionHistory getByID(UUID ID);

    Integer updateByID(UUID ID, TransactionHistory transactionHistoryToUpdate);

    Integer deleteByID(UUID ID);
}
