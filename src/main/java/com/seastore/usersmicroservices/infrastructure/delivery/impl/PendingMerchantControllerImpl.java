package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.auth.services.PendingMerchantService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.PendingMerchantController;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")

public class PendingMerchantControllerImpl implements PendingMerchantController {

    private final PendingMerchantService merchantPendingService;

    public PendingMerchantControllerImpl(PendingMerchantService merchantPendingService) {
        this.merchantPendingService = merchantPendingService;
    }


    @GetMapping("/register/merchant/pending")
    public ResponseEntity<List<User>> getAllPendingMerchant() {
        return merchantPendingService.getAll();
    }

    @PutMapping("/register/merchant/pending/{id}/accept")
    public ResponseEntity<Object> acceptPendingMerchant(@PathVariable("id") UUID ID) {
        return merchantPendingService.accept(ID);
    }

    @PutMapping("/register/merchant/pending/{id}/reject")
    public ResponseEntity<Object> rejectPendingMerchant(@PathVariable("id") UUID ID) {
        return merchantPendingService.reject(ID);
    }

}
