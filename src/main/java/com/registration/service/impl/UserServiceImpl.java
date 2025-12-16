package com.registration.service.impl;

import com.registration.dto.UserRegistrationDTO;
import com.registration.dto.UserResponseDTO;
import com.registration.entity.User;
import com.registration.exception.DuplicateResourceException;
import com.registration.exception.ResourceNotFoundException;
import com.registration.repository.UserRepository;
import com.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of UserService interface
 * Contains business logic for user operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registerUser(UserRegistrationDTO registrationDTO) {
        log.info("Attempting to register user with email: {}", registrationDTO.getEmail());

        // Validate email uniqueness
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            log.error("Email already exists: {}", registrationDTO.getEmail());
            throw new DuplicateResourceException("Email already registered: " + registrationDTO.getEmail());
        }

        // Validate username uniqueness
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            log.error("Username already exists: {}", registrationDTO.getUsername());
            throw new DuplicateResourceException("Username already taken: " + registrationDTO.getUsername());
        }

        // Map DTO to Entity
        User user = modelMapper.map(registrationDTO, User.class);

        // Encode password
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getUserId());

        // Map Entity to Response DTO
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> getUserById(Long userId) {
        log.debug("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserResponseDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> getUserByEmail(String email) {
        log.debug("Fetching user with email: {}", email);
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserResponseDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> getUserByUsername(String username) {
        log.debug("Fetching user with username: {}", username);
        return userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserResponseDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getActiveUsers() {
        log.debug("Fetching active users");
        return userRepository.findByIsActiveTrue().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRegistrationDTO registrationDTO) {
        log.info("Updating user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Check if email is being changed and if it's already taken
        if (!existingUser.getEmail().equals(registrationDTO.getEmail()) &&
            userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + registrationDTO.getEmail());
        }

        // Check if username is being changed and if it's already taken
        if (!existingUser.getUsername().equals(registrationDTO.getUsername()) &&
            userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new DuplicateResourceException("Username already in use: " + registrationDTO.getUsername());
        }

        // Update fields
        modelMapper.map(registrationDTO, existingUser);
        
        // Update password if changed
        if (registrationDTO.getPassword() != null && !registrationDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        log.info("User updated successfully with ID: {}", userId);

        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    @Override
    public void deactivateUser(Long userId) {
        log.info("Deactivating user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        user.setIsActive(false);
        userRepository.save(user);

        log.info("User deactivated successfully with ID: {}", userId);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }

        userRepository.deleteById(userId);
        log.info("User deleted successfully with ID: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchUsers(String searchTerm) {
        log.debug("Searching users with term: {}", searchTerm);
        return userRepository.searchUsersByName(searchTerm).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
