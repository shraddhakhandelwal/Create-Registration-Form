package com.registration.controller;

import com.registration.dto.UserRegistrationDTO;
import com.registration.dto.UserResponseDTO;
import com.registration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for User Registration and Management
 * Provides RESTful API endpoints
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;

    /**
     * Register a new user
     * POST /api/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        log.info("REST API: Registering new user with email: {}", registrationDTO.getEmail());
        UserResponseDTO response = userService.registerUser(registrationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get user by ID
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long userId) {
        log.info("REST API: Fetching user with ID: {}", userId);
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all users
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.info("REST API: Fetching all users");
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get active users
     * GET /api/users/active
     */
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDTO>> getActiveUsers() {
        log.info("REST API: Fetching active users");
        List<UserResponseDTO> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Search users by name
     * GET /api/users/search?term={searchTerm}
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam("term") String searchTerm) {
        log.info("REST API: Searching users with term: {}", searchTerm);
        List<UserResponseDTO> users = userService.searchUsers(searchTerm);
        return ResponseEntity.ok(users);
    }

    /**
     * Update user
     * PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") Long userId,
            @Valid @RequestBody UserRegistrationDTO registrationDTO) {
        log.info("REST API: Updating user with ID: {}", userId);
        UserResponseDTO response = userService.updateUser(userId, registrationDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Deactivate user
     * PATCH /api/users/{id}/deactivate
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long userId) {
        log.info("REST API: Deactivating user with ID: {}", userId);
        userService.deactivateUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete user
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        log.info("REST API: Deleting user with ID: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Check if email exists
     * GET /api/users/check/email/{email}
     */
    @GetMapping("/check/email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable("email") String email) {
        boolean exists = userService.isEmailExists(email);
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if username exists
     * GET /api/users/check/username/{username}
     */
    @GetMapping("/check/username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable("username") String username) {
        boolean exists = userService.isUsernameExists(username);
        return ResponseEntity.ok(exists);
    }
}
