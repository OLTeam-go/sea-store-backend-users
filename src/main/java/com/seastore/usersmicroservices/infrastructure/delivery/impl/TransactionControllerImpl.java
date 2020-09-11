package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.wallet.services.TransactionService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.TransactionController;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.WalletTransactionContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/wallets")
@CrossOrigin(origins = "*")
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    public TransactionControllerImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    @PutMapping("/transactions/{id}/credit")
    public ResponseEntity<Object> creditByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract) {
        return transactionService.creditByID(ID, walletTransactionContract.getAmount());
    }

    @Override
    @PutMapping("/transactions/{id}/debit")
    public ResponseEntity<Object> debitByID(
            @PathVariable("id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract) {
        return transactionService.debitByID(ID, walletTransactionContract.getAmount());
    }

    @Override
    @PutMapping("/transactions/users/{user_id}/credit")
    public ResponseEntity<Object> creditByUserID(
            @PathVariable("user_id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract) {
        return transactionService.creditByUserID(ID, walletTransactionContract.getAmount());
    }

    @Override
    @PutMapping("/transactions/users/{user_id}/debit")
    public ResponseEntity<Object> debitByUserID(
            @PathVariable("user_id") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract) {
        return transactionService.debitByUserID(ID, walletTransactionContract.getAmount());
    }

}