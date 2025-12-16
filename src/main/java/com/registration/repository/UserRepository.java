package com.registration.repository;

import com.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 * Extends JpaRepository for CRUD operations and custom queries
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email
     * @param email User email
     * @return Optional containing user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by username
     * @param username User username
     * @return Optional containing user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if email already exists
     * @param email User email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Check if username already exists
     * @param username User username
     * @return true if exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Find all active users
     * @return List of active users
     */
    List<User> findByIsActiveTrue();

    /**
     * Find users by city
     * @param city City name
     * @return List of users in the specified city
     */
    List<User> findByCity(String city);

    /**
     * Find users by state
     * @param state State name
     * @return List of users in the specified state
     */
    List<User> findByState(String state);

    /**
     * Custom query to find users by email or username
     * @param email User email
     * @param username User username
     * @return Optional containing user if found
     */
    @Query("SELECT u FROM User u WHERE u.email = :email OR u.username = :username")
    Optional<User> findByEmailOrUsername(@Param("email") String email, 
                                          @Param("username") String username);

    /**
     * Search users by name (first or last name)
     * @param searchTerm Search term
     * @return List of matching users
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchUsersByName(@Param("searchTerm") String searchTerm);
}
