package com.burakejder.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserProfileUpdate {

    @Size(max = 15, message = "First name can not exceed 15 characters")
    private String firstName;

    @Size(max = 15, message = "Last name can not exceed 15 characters")
    private String lastName;

    private Date birthDate;
    private String address;
    private String phoneNumber;
}
