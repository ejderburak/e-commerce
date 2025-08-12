package com.burakejder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserProfile {

    private Long userId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String address;
    private String phoneNumber;
}
