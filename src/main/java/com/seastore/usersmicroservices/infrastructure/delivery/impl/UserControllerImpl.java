package com.seastore.usersmicroservices.infrastructure.delivery.impl;


import com.seastore.usersmicroservices.core.user.services.UserService;
import com.seastore.usersmicroservices.infrastructure.delivery.controllers.UserController;
import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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


    @GetMapping("/{id}")
    public ResponseEntity<User> getByID(@PathVariable("id") UUID ID) {
        return userService.getByID(ID);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateByID(@PathVariable("id") UUID ID,
                                             @RequestBody User userToUpdate) {
        return userService.updateByID(ID, userToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable("id") UUID ID) {
        return userService.deleteByID(ID);
    }

//    @GetMapping
//    public ResponseEntity<User> getByUsername(@RequestParam("username") String username) {
//        return userService.getByUsername(username);
//    }

//    @PutMapping
//    public ResponseEntity<Object> updateByUsername(@RequestParam("username") String username,
//                                                   @RequestBody User userToUpdate) {
//        return userService.updateByUsername(username, userToUpdate);
//    }

    @PutMapping("/setting")
    public ResponseEntity<Object> updateByUsernameAndPassword(
            @PathVariable("id") UUID ID,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestBody User userToUpdate) {
        return userService.updateByUsernameAndPassword(ID, username, password, userToUpdate);
    }

//    @DeleteMapping
//    public ResponseEntity<Object> deleteByUsername(@RequestParam("username") String username) {
//        return userService.deleteByUsername(username);
//    }

}