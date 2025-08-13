package com.burakejder.repository;

import com.burakejder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    // find user by email
    Optional<User> findByEmail(String email);

    // find user by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // email db check
    boolean existsByEmail(String email);

    // phoneNumber db check
    boolean existsByPhoneNumber(String phoneNumber);
}
