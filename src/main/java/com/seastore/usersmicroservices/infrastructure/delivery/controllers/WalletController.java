package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.WalletTransactionContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface WalletController {
    @PostMapping
    public Wallet create(
            @RequestBody Wallet wallet
    );

    @GetMapping
    public List<Wallet> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getByID(
            @PathVariable("id") UUID ID
    );

    @GetMapping("/username/{username}")
    public ResponseEntity<Wallet> getByUsername(
            @PathVariable("username") String username
    );

    @GetMapping("/userid/{userid}")
    public ResponseEntity<Wallet> getByUserID(
            @PathVariable("userid") UUID userID
    );

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(
            @PathVariable("id") UUID ID,
            @RequestBody Wallet walltToUpdate
    );

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(
            @PathVariable("id") UUID ID
    );

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

    @PutMapping("userid/{userid}/credit")
    public ResponseEntity<Object> creditByUserID(
            @PathVariable("userid") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

    @PutMapping("userid/{userid}/debit")
    public ResponseEntity<Object> debitByUserID(
            @PathVariable("userid") UUID ID,
            @RequestBody WalletTransactionContract walletTransactionContract
    );

}

