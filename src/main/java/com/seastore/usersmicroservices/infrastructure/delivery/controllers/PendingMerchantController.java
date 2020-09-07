package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

public interface PendingMerchantController {

    @GetMapping("/register/merchant/pending")
    public ResponseEntity<List<User>> getAllPendingMerchant();

    @PutMapping("/register/merchant/pending/{id}/accept")
    public ResponseEntity<Object> acceptPendingMerchant(@PathVariable("id") UUID ID);

    @PutMapping("/register/merchant/pending/{id}/reject")
    public ResponseEntity<Object> rejectPendingMerchant(@PathVariable("id") UUID ID);
}

