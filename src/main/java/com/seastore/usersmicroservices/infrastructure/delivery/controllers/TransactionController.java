package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.contracts.WalletTransactionContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface TransactionController {

    @PutMapping("/{id}/credit")
    public ResponseEntity<Object> creditByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/{id}/debit")
    public ResponseEntity<Object> debitByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/users/{user_id}/credit")
    public ResponseEntity<Object> creditByUserID(
            @PathVariable("user_id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/users/{user_id}/debit")
    public ResponseEntity<Object> debitByUserID(
            @PathVariable("user_id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/username/{username}/credit")
    public ResponseEntity<Object> creditByUserUsername(
            @PathVariable("username") String username,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/username/{username}/debit")
    public ResponseEntity<Object> debitByUserUsername(
            @PathVariable("username") String username,
            @RequestBody WalletTransactionContract walletTransactionContract
    );
}

