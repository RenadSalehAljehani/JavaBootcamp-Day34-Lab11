package com.example.bolgsystem_lab11.Controller;

import com.example.bolgsystem_lab11.ApiResponse.ApiResponse;
import com.example.bolgsystem_lab11.Model.User;
import com.example.bolgsystem_lab11.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {

    // 1. Declare a dependency for UserService using Dependency Injection
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New User Added."));
    }

    // 2.3 Update
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(userId, user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted."));
    }

    // 3. Extra endpoint
    // 1.3  Endpoint to search for a user by username
    @GetMapping("/searchByUsername/{username}")
    public ResponseEntity searchByUsername(@PathVariable String username) {
        User user = userService.searchByUsername(username);
        return ResponseEntity.status(200).body(user);
    }

    // 2.3  Endpoint to check user registration date by id
    @GetMapping("/checkRegistrationDate/{userId}")
    public ResponseEntity checkRegistrationDate(@PathVariable Integer userId) {
        LocalDateTime registrationDate = userService.checkRegistrationDate(userId);
        return ResponseEntity.status(200).body(registrationDate);
    }
}