package com.seastore.usersmicroservices.infrastructure.delivery.controllers;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @PostMapping
    public User create(@RequestBody User user);

    @GetMapping
    public List<User> getAll();

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username);

    @PutMapping("/{username}")
    public ResponseEntity<Integer> updateByUsernameAndPassword(@PathVariable("username") String username,
                                                               @RequestBody User userToUpdate);

    @DeleteMapping("/{username}")
    public Integer deleteByUsername(@PathVariable("username") String username);

}

