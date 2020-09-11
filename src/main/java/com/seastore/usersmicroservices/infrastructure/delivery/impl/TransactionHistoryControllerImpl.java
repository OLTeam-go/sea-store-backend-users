package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.transactionHistory.services.TransactionHistoryService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.TransactionHistoryController;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/transactions/history")
@CrossOrigin(origins = "*")
public class TransactionHistoryControllerImpl implements TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryControllerImpl(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @Override
    @PostMapping
    public TransactionHistory create(@RequestBody TransactionHistory transactionHistory) {
        return transactionHistoryService.create(transactionHistory);
    }

    @Override
    @GetMapping
    public List<TransactionHistory> getAll() {
        return transactionHistoryService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistory> getByID(@PathVariable("id") UUID ID) {
        return transactionHistoryService.getByID(ID);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(@PathVariable("id") UUID ID,
                                             @RequestBody TransactionHistory transactionHistoryToUpdate) {
        return transactionHistoryService.updateByID(ID, transactionHistoryToUpdate);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable("id") UUID ID) {
        return transactionHistoryService.deleteByID(ID);
    }
}