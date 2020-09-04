package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.delivery.converters.UserSettingContract;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserController {
    @PostMapping
    public User create(
            @RequestBody User user
    );

    @GetMapping
    public List<User> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<User> getByID(
            @PathVariable("id") UUID ID
    );

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(
            @PathVariable("id") UUID ID,
            @RequestBody User userToUpdate
    );

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(
            @PathVariable("id") UUID ID
    );

//    @GetMapping
//    public ResponseEntity<User> getByUsername(@RequestParam("username") String username);

//    @PutMapping
//    public ResponseEntity<Object> updateByUsername(@RequestParam("username") String username,
//                                                   @RequestBody User userToUpdate);

    @PutMapping("/{id}/setting")
    public ResponseEntity<Object> updateByUsernameAndPassword(
            @PathVariable("id") UUID ID,
            @RequestBody UserSettingContract userSettingContract
    );
//
//    @DeleteMapping
//    public ResponseEntity<Object> deleteByUsername(@RequestParam("username") String username);

}

