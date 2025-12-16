package com.registration.service;

import com.registration.dto.UserRegistrationDTO;
import com.registration.dto.UserResponseDTO;
import com.registration.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for User operations
 * Defines business logic contract
 */
public interface UserService {

    /**
     * Register a new user
     * @param registrationDTO User registration data
     * @return Registered user response
     */
    UserResponseDTO registerUser(UserRegistrationDTO registrationDTO);

    /**
     * Get user by ID
     * @param userId User ID
     * @return User response DTO
     */
    Optional<UserResponseDTO> getUserById(Long userId);

    /**
     * Get user by email
     * @param email User email
     * @return User response DTO
     */
    Optional<UserResponseDTO> getUserByEmail(String email);

    /**
     * Get user by username
     * @param username User username
     * @return User response DTO
     */
    Optional<UserResponseDTO> getUserByUsername(String username);

    /**
     * Get all users
     * @return List of all users
     */
    List<UserResponseDTO> getAllUsers();

    /**
     * Get all active users
     * @return List of active users
     */
    List<UserResponseDTO> getActiveUsers();

    /**
     * Update user information
     * @param userId User ID
     * @param registrationDTO Updated user data
     * @return Updated user response
     */
    UserResponseDTO updateUser(Long userId, UserRegistrationDTO registrationDTO);

    /**
     * Deactivate user account
     * @param userId User ID
     */
    void deactivateUser(Long userId);

    /**
     * Delete user permanently
     * @param userId User ID
     */
    void deleteUser(Long userId);

    /**
     * Search users by name
     * @param searchTerm Search term
     * @return List of matching users
     */
    List<UserResponseDTO> searchUsers(String searchTerm);

    /**
     * Verify if email exists
     * @param email Email address
     * @return true if exists
     */
    boolean isEmailExists(String email);

    /**
     * Verify if username exists
     * @param username Username
     * @return true if exists
     */
    boolean isUsernameExists(String username);
}
