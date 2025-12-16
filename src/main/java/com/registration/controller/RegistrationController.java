package com.registration.controller;

import com.registration.dto.UserRegistrationDTO;
import com.registration.dto.UserResponseDTO;
import com.registration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * MVC Controller for User Registration Web Pages
 * Handles Thymeleaf view rendering
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserService userService;

    /**
     * Display home page
     * GET /
     */
    @GetMapping
    public String home() {
        return "index";
    }

    /**
     * Display registration form
     * GET /register
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        log.info("Displaying registration form");
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "registration-form";
    }

    /**
     * Process registration form submission
     * POST /register
     */
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("userRegistrationDTO") UserRegistrationDTO registrationDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.info("Processing registration for email: {}", registrationDTO.getEmail());

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            log.error("Validation errors found in registration form");
            return "registration-form";
        }

        try {
            // Register user
            UserResponseDTO response = userService.registerUser(registrationDTO);
            log.info("User registered successfully with ID: {}", response.getUserId());

            // Add success message
            redirectAttributes.addFlashAttribute("successMessage", 
                "Registration successful! Welcome, " + response.getFirstName() + "!");
            redirectAttributes.addFlashAttribute("user", response);

            return "redirect:/registration-success";

        } catch (Exception e) {
            log.error("Error during registration: {}", e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "registration-form";
        }
    }

    /**
     * Display registration success page
     * GET /registration-success
     */
    @GetMapping("/registration-success")
    public String registrationSuccess() {
        return "registration-success";
    }

    /**
     * Display all users
     * GET /users
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        log.info("Displaying all users");
        List<UserResponseDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Display user details
     * GET /users/{id}
     */
    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable("id") Long userId, Model model, RedirectAttributes redirectAttributes) {
        log.info("Displaying user with ID: {}", userId);
        
        return userService.getUserById(userId)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "user-details";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "User not found");
                    return "redirect:/users";
                });
    }

    /**
     * Display edit user form
     * GET /users/{id}/edit
     */
    @GetMapping("/users/{id}/edit")
    public String showEditForm(@PathVariable("id") Long userId, Model model, RedirectAttributes redirectAttributes) {
        log.info("Displaying edit form for user ID: {}", userId);
        
        return userService.getUserById(userId)
                .map(user -> {
                    UserRegistrationDTO dto = new UserRegistrationDTO();
                    // Map user to DTO
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setUsername(user.getUsername());
                    dto.setEmail(user.getEmail());
                    dto.setPhoneNumber(user.getPhoneNumber());
                    dto.setDateOfBirth(user.getDateOfBirth());
                    dto.setGender(user.getGender());
                    dto.setAddress(user.getAddress());
                    dto.setCity(user.getCity());
                    dto.setState(user.getState());
                    dto.setPostalCode(user.getPostalCode());
                    dto.setCountry(user.getCountry());
                    
                    model.addAttribute("userId", userId);
                    model.addAttribute("userRegistrationDTO", dto);
                    return "edit-user";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "User not found");
                    return "redirect:/users";
                });
    }

    /**
     * Process edit user form
     * POST /users/{id}/edit
     */
    @PostMapping("/users/{id}/edit")
    public String updateUser(
            @PathVariable("id") Long userId,
            @Valid @ModelAttribute("userRegistrationDTO") UserRegistrationDTO registrationDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.info("Updating user with ID: {}", userId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", userId);
            return "edit-user";
        }

        try {
            userService.updateUser(userId, registrationDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
            return "redirect:/users/" + userId;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            model.addAttribute("userId", userId);
            model.addAttribute("errorMessage", e.getMessage());
            return "edit-user";
        }
    }

    /**
     * Delete user
     * POST /users/{id}/delete
     */
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
        log.info("Deleting user with ID: {}", userId);
        
        try {
            userService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/users";
    }
}
