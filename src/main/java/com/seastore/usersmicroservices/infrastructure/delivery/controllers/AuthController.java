package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.LoginContract;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterAdminContract;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterCustomerContract;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.RegisterMerchantContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginContract loginContract);

    @PostMapping("/register/customer")
    public ResponseEntity<User> registerCustomer(@RequestBody RegisterCustomerContract registerCustomerContract);

    @PostMapping("/register/merchant")
    public ResponseEntity<User> registerMerchant(@RequestBody RegisterMerchantContract registerMerchantContract);

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterAdminContract registerAdminContract);
}

