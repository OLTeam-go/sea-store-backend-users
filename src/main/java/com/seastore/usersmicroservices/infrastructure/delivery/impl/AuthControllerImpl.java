package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.auth.services.AuthService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.AuthController;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.*;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Autowired
    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<User> authLogin(@RequestBody LoginContract loginContract) {
        return authService.login(loginContract);
    }

    @Override
    @PostMapping("/register/option/active")
    public ResponseEntity<Integer> setActiveByUsername(@RequestBody RegisterOptionActiveContract registerOptionActiveContract) {
        return authService.setActiveByUsername(registerOptionActiveContract.getUsername());
    }

    @Override
    @PostMapping("/register/customer")
    public ResponseEntity<User> authRegisterCustomer(@RequestBody RegisterCustomerContract registerCustomerContract) {
        return authService.registerCustomer(registerCustomerContract);
    }

    @Override
    @PostMapping("/register/merchant")
    public ResponseEntity<User> authRegisterMerchant(RegisterMerchantContract registerMerchantContract) {
        return authService.registerMerchant(registerMerchantContract);
    }

    @Override
    @PostMapping("/register/admin")
    public ResponseEntity<User> authRegisterAdmin(RegisterAdminContract registerAdminContract) {
        return authService.registerAdmin(registerAdminContract);
    }

}
