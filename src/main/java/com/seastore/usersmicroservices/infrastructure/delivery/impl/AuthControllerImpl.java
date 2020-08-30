package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.auth.services.SettingService;
import com.seastore.usersmicroservices.core.auth.services.SignInService;
import com.seastore.usersmicroservices.core.auth.services.SignUpService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.AuthController;
import com.seastore.usersmicroservices.infrastructure.delivery.converters.*;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthControllerImpl implements AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final SettingService settingService;

    public AuthControllerImpl(SignUpService signUpService, SignInService signInService, SettingService settingService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
        this.settingService = settingService;
    }


    @Override
    @PostMapping("/login")
    public ResponseEntity<User> authLogin(@RequestBody LoginContract loginContract) {
        return signInService.login(loginContract);
    }

    @Override
    @PostMapping("/option/{username}/active")
    public ResponseEntity<Object> setActiveByUsername(@PathVariable("username") String username, @RequestBody OptionActiveContract optionActiveContract) {
        return settingService.setActiveByUsername(username, optionActiveContract);
    }

    @Override
    @PostMapping("/register/customer")
    public ResponseEntity<User> authRegisterCustomer(@RequestBody RegisterCustomerContract registerCustomerContract) {
        return signUpService.registerCustomer(registerCustomerContract);
    }

    @Override
    @PostMapping("/register/merchant")
    public ResponseEntity<User> authRegisterMerchant(RegisterMerchantContract registerMerchantContract) {
        return signUpService.registerMerchant(registerMerchantContract);
    }

    @Override
    @PostMapping("/register/admin")
    public ResponseEntity<User> authRegisterAdmin(RegisterAdminContract registerAdminContract) {
        return signUpService.registerAdmin(registerAdminContract);
    }

}
