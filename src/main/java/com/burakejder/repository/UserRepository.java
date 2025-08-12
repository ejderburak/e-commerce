package com.burakejder.repository;

import com.burakejder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    // Find user by email for login
    Optional<User> findByEmail(String email);

    // Find user for phone number validation
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Email db check
    boolean existsByEmail(String email);

    // phoneNumber db check
    boolean existsByPhoneNumber(String phoneNumber);
}
