package com.burakejder.service;

import com.burakejder.DTO.*;
import com.burakejder.entities.User;
import com.burakejder.mapper.DtoMapper;
import com.burakejder.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register new user
    public DtoAuthResponse register(DtoUserRegister registerDto) {
        try {
            // Check if email already exists
            if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
                return new DtoAuthResponse("Email already exists", false);
            }

            // Check if phone number already exists
            if (userRepository.findByPhoneNumber(registerDto.getPhoneNumber()).isPresent()) {
                return new DtoAuthResponse("Phone number already exists", false);
            }

            // Create new user
            User user = DtoMapper.toEntity(registerDto);
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            User savedUser = userRepository.save(user);
            DtoUserProfile profileDto = DtoMapper.toProfileDto(savedUser);

            return new DtoAuthResponse("User registered successfully", true, profileDto);

        } catch (Exception e) {
            return new DtoAuthResponse("Registration failed: " + e.getMessage(), false);
        }
    }

    // Login user
    public DtoAuthResponse login(DtoUserLogin loginDto) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());

            if (userOptional.isEmpty()) {
                return new DtoAuthResponse("User not found", false);
            }

            User user = userOptional.get();

            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return new DtoAuthResponse("Invalid password", false);
            }

            DtoUserProfile profileDto = DtoMapper.toProfileDto(user);
            return new DtoAuthResponse("Login successful", true, profileDto);

        } catch (Exception e) {
            return new DtoAuthResponse("Login failed: " + e.getMessage(), false);
        }
    }

    // Get user profile
    public DtoUserProfile getUserProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }

        return DtoMapper.toProfileDto(userOptional.get());
    }

    // Update user profile
    public DtoUserProfile updateProfile(Long userId, DtoUserProfileUpdate updateDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        DtoMapper.updateUserFromDto(user, updateDto);

        User updatedUser = userRepository.save(user);
        return DtoMapper.toProfileDto(updatedUser);
    }

    // Add/Update address
    public DtoUserProfile updateAddress(Long userId, DtoUserAddress addressDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        user.setAddress(addressDto.getAddress());

        User updatedUser = userRepository.save(user);
        return DtoMapper.toProfileDto(updatedUser);
    }
}

