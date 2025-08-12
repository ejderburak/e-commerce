package com.burakejder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAuthResponse {

    private String message;
    private boolean success;
    private DtoUserProfile user;

    public DtoAuthResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }


}
