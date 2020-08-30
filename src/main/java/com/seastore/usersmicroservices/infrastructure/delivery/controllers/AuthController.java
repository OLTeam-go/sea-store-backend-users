package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.*;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @PostMapping("/setting/{username}/active")
    public ResponseEntity<Object> setActiveByUsername(@PathVariable("username") String username, @RequestBody OptionActiveContract optionActiveContract);

    @PostMapping("/login")
    public ResponseEntity<User> authLogin(@RequestBody LoginContract loginContract);

    @PostMapping("/register/customer")
    public ResponseEntity<User> authRegisterCustomer(@RequestBody RegisterCustomerContract registerCustomerContract);


    @PostMapping("/register/merchant")
    public ResponseEntity<User> authRegisterMerchant(@RequestBody RegisterMerchantContract registerMerchantContract);


    @PostMapping("/register/admin")
    public ResponseEntity<User> authRegisterAdmin(@RequestBody RegisterAdminContract registerAdminContract);
}

