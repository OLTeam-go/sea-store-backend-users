package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.user.services.UserService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.UserController;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Integer> updateByUsernameAndPassword(@PathVariable("username") String username,
                                                               @RequestBody User userToUpdate) {
        return userService.updateByUsernameAndPassword(username, userToUpdate);
    }
}