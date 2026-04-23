package com.micro.authservice.dto.response.auth;

import com.micro.authservice.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TokenValidationResponse implements Serializable {
    private boolean valid;
    private UserDetailsDto userDetails;
}
