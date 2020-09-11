package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.WalletTransactionContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface TransactionController {

    @PutMapping("/transactions/{id}/credit")
    public ResponseEntity<Object> creditByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/transactions/{id}/debit")
    public ResponseEntity<Object> debitByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/transactions/userid/{userid}/credit")
    public ResponseEntity<Object> creditByUserID(
            @PathVariable("userid") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("/transactions/userid/{userid}/debit")
    public ResponseEntity<Object> debitByUserID(
            @PathVariable("userid") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

}

