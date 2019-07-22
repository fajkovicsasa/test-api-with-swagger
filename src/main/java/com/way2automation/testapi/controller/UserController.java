package com.way2automation.testapi.controller;

import com.way2automation.testapi.exception.UserNotFoundException;
import com.way2automation.testapi.model.User;
import com.way2automation.testapi.service.UserService;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getId() != null) {
            return new ResponseEntity<>("User ID must not be defined when creating a new user!", HttpStatus.NOT_MODIFIED);
        }

        try {
            this.userService.createUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating user!", HttpStatus.NOT_MODIFIED);
        }

    }

    @PutMapping
    public ResponseEntity<String> updatUser(@RequestBody User user) {
        if (user.getId() == null) {
            return new ResponseEntity<>("User ID must be defined when updating a user!", HttpStatus.NOT_MODIFIED);
        }

        try {
            this.userService.updateUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating user!", HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting user!", HttpStatus.NOT_MODIFIED);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> patchUser(@PathVariable Long userId, @RequestBody User user) {
        if (user.getId() != null) {
            return new ResponseEntity<>("User ID must not be defined in the Request body when patching a user!", HttpStatus.NOT_MODIFIED);
        }
        user.setId(userId);

        try {
            this.userService.patchUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while patching user!", HttpStatus.NOT_MODIFIED);
        }
    }


}


