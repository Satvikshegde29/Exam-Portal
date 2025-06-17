package com.examportal.service;

import com.examportal.model.User;

public interface UserService {
    User registerUser(User user);
    boolean authenticateUser(String email, String password);
    User getUserById(Long userId);
    User updateUser(Long userId, User updatedUser);
    User getUserByEmail(String email); // Add this method
}
