package com.example.bolgsystem_lab11.Service;

import com.example.bolgsystem_lab11.ApiResponse.ApiException;
import com.example.bolgsystem_lab11.Model.User;
import com.example.bolgsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    // 1. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 2. CRUD
    // 2.1 Get
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2.2 Post
    public void addUser(User user) {
        userRepository.save(user);
    }

    // 2.3 Update
    public void updateUser(Integer userId, User user) {
        User oldUser = userRepository.findUserByUserId(userId);
        if (oldUser == null) {
            throw new ApiException("User Not Found.");
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

    // 2.4 Delete
    public void deleteUser(Integer userId) {
        User oldUser = userRepository.findUserByUserId(userId);
        if (oldUser == null) {
            throw new ApiException("User Not Found.");
        }
        userRepository.delete(oldUser);
    }

    // 3. Extra endpoint
    // 3.1 Endpoint to search for a user by username
    public User searchByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("User Not Found.");
        }
        return user;
    }

    // 3.2 Endpoint to check user registration date by id
    public LocalDateTime checkRegistrationDate(Integer userId) {
        if (userRepository.findUserByUserId(userId) == null) {
            throw new ApiException("User Not Found.");
        }
        return userRepository.findRegistrationDateByUserId(userId);
    }
}