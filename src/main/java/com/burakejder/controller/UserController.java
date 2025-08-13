package com.burakejder.controller;


import com.burakejder.DTO.DtoUserAddress;
import com.burakejder.DTO.DtoUserProfile;
import com.burakejder.DTO.DtoUserProfileUpdate;
import com.burakejder.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Data
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // /api/v1/users/profile
    @GetMapping("/profile")
    public ResponseEntity<DtoUserProfile> getProfile(@RequestParam Long userId) {
        DtoUserProfile profile = userService.getUserProfile(userId);

        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // /api/v1/users/profile
    @PutMapping("/profile")
    public ResponseEntity<DtoUserProfile> updateProfile(
            @RequestParam Long userId,
            @Valid @RequestBody DtoUserProfileUpdate updateDto) {

        DtoUserProfile updatedProfile = userService.updateProfile(userId, updateDto);

        if (updatedProfile != null) {
            return ResponseEntity.ok(updatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST -- /api/v1/users/address -- add address
    @PostMapping("/address")
    public ResponseEntity<DtoUserProfile> addAddress(
            @RequestParam Long userId,
            @Valid @RequestBody DtoUserAddress addressDto) {

        DtoUserProfile updatedProfile = userService.updateAddress(userId, addressDto);

        if (updatedProfile != null) {
            return ResponseEntity.ok(updatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
