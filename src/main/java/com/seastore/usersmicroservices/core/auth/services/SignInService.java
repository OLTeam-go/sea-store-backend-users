package com.seastore.usersmicroservices.core.auth.services;


import com.seastore.usersmicroservices.infrastructure.delivery.converters.LoginContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    private final UserRepo userRepo;

    @Autowired
    public SignInService(@Qualifier("User") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<User> login(LoginContract loginContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userRepo.getByUsernameAndPassword(
                    loginContract.getUsername(),
                    loginContract.getPassword());

            if (!findUser.getActive()) {
                findUser = null;
                status = HttpStatus.UNAUTHORIZED;
            } else {
                status = HttpStatus.OK;
            }
        } catch (EmptyResultDataAccessException e) {
            findUser = null;
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<User>(findUser, status);
    }

}
