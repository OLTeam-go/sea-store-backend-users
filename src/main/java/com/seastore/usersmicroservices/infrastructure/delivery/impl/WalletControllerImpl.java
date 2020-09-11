package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.wallet.services.WalletService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.WalletController;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/wallets")
@CrossOrigin(origins = "*")
public class WalletControllerImpl implements WalletController {

    private final WalletService walletService;

    public WalletControllerImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @PostMapping
    public Wallet create(@RequestBody Wallet wallet) {
        return walletService.create(wallet);
    }

    @Override
    @GetMapping
    public List<Wallet> getAll() {
        return walletService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getByID(@PathVariable("id") UUID ID) {
        return walletService.getByID(ID);
    }

    @Override
    @GetMapping("/username/{username}")
    public ResponseEntity<Wallet> getByUsername(
            @PathVariable("username") String username
    ) {
        return walletService.getByUsername(username);
    }

    @Override
    @GetMapping("/users/{user_id}")
    public ResponseEntity<Wallet> getByUserID(
            @PathVariable("user_id") UUID userID
    ) {
        return walletService.getByUserID(userID);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(@PathVariable("id") UUID ID,
                                             @RequestBody Wallet walletToUpdate) {
        return walletService.updateByID(ID, walletToUpdate);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable("id") UUID ID) {
        return walletService.deleteByID(ID);
    }

}