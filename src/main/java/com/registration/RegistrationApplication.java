package com.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main Spring Boot Application Class
 * Entry point for the User Registration System
 * 
 * @author Registration System
 * @version 1.0.0
 */
@SpringBootApplication
public class RegistrationApplication {

    /**
     * Main method to start the Spring Boot application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
        System.out.println("\n" +
                "========================================\n" +
                "  User Registration System Started!\n" +
                "  Access at: http://localhost:8080\n" +
                "  API Docs: http://localhost:8080/api/users\n" +
                "========================================\n");
    }

    /**
     * ModelMapper Bean for DTO to Entity mapping
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * PasswordEncoder Bean for password encryption
     * Uses BCrypt hashing algorithm
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
