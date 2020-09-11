package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface TransactionHistoryController {
    @PostMapping
    public TransactionHistory create(
            @RequestBody TransactionHistory transactionHistory
    );

    @GetMapping
    public List<TransactionHistory> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistory> getByID(
            @PathVariable("id") UUID ID
    );

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(
            @PathVariable("id") UUID ID,
            @RequestBody TransactionHistory transactionHistory
    );

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(
            @PathVariable("id") UUID ID
    );
}

