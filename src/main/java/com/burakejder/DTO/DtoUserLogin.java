package com.burakejder.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserLogin {

    @NotBlank
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
