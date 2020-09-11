package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.auth.services.PendingMerchantService;
import com.seastore.usersmicroservices.core.auth.services.SignInService;
import com.seastore.usersmicroservices.core.auth.services.SignUpService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.AuthController;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.LoginContract;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterAdminContract;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterCustomerContract;
import com.seastore.usersmicroservices.infrastructure.delivery.contracts.RegisterMerchantContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthControllerImpl implements AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final PendingMerchantService merchantPendingService;

    public AuthControllerImpl(SignUpService signUpService, SignInService signInService, PendingMerchantService merchantPendingService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
        this.merchantPendingService = merchantPendingService;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginContract loginContract) {
        return signInService.login(loginContract);
    }

    @Override
    @PostMapping("/register/customer")
    public ResponseEntity<User> registerCustomer(@RequestBody RegisterCustomerContract registerCustomerContract) {
        return signUpService.registerCustomer(registerCustomerContract);
    }

    @Override
    @PostMapping("/register/merchant")
    public ResponseEntity<User> registerMerchant(RegisterMerchantContract registerMerchantContract) {
        return signUpService.registerMerchant(registerMerchantContract);
    }

    @Override
    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(RegisterAdminContract registerAdminContract) {
        return signUpService.registerAdmin(registerAdminContract);
    }

}
