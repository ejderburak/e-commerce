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

    // registering new user
    public DtoAuthResponse register(DtoUserRegister registerDto) {

        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        if(userRepository.findByPhoneNumber(registerDto.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        // create new user
        User user = DtoMapper.toEntity(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        User savedUser = userRepository.save(user);
        DtoUserProfile profileDto = DtoMapper.toProfileDto(savedUser);

        return new DtoAuthResponse("User registered successfully", true, profileDto);
    }



    // login
    public DtoAuthResponse login(DtoUserLogin loginDto) {

        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if(userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }

        User user = userOptional.get();
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        DtoUserProfile profileDto = DtoMapper.toProfileDto(user);

        return new DtoAuthResponse("Logged in successfully",true  ,profileDto);
    }

    // get user profile
    public DtoUserProfile getUserProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
           throw new IllegalArgumentException("User not found");
        }

        return DtoMapper.toProfileDto(userOptional.get());
    }

    // update user profile
    public DtoUserProfile updateProfile(Long userId, DtoUserProfileUpdate updateDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();
        DtoMapper.updateUserFromDto(user, updateDto);

        User updatedUser = userRepository.save(user);
        return DtoMapper.toProfileDto(updatedUser);
    }

    // updating address
    public DtoUserProfile updateAddress(Long userId, DtoUserAddress addressDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();
        user.setAddress(addressDto.getAddress());

        User updatedUser = userRepository.save(user);
        return DtoMapper.toProfileDto(updatedUser);
    }
}