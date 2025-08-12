package com.burakejder.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserRegister {

    @NotBlank(message = "First name is required")
    @Size(max = 15, message = "First name can't be more than 15 characters")
    private String firstName;


    @NotBlank(message = "Last name is required")
    @Size(max = 15, message = "Last name can't be more than 15 characters")
    private String lastName;

    private Date birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;

    private String address;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
