package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.*;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @PostMapping("/login")
    public ResponseEntity<User> authLogin(@RequestBody LoginContract loginContract);

    @PostMapping("/register/option/active")
    public ResponseEntity<Integer> setActiveByUsername(@RequestBody RegisterOptionActiveContract registerOptionActiveContract);


    @PostMapping("/register/customer")
    public ResponseEntity<User> authRegisterCustomer(@RequestBody RegisterCustomerContract registerCustomerContract);


    @PostMapping("/register/merchant")
    public ResponseEntity<User> authRegisterMerchant(@RequestBody RegisterMerchantContract registerMerchantContract);


    @PostMapping("/register/admin")
    public ResponseEntity<User> authRegisterAdmin(@RequestBody RegisterAdminContract registerAdminContract);
}

