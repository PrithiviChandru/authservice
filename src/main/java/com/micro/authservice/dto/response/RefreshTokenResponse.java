package com.micro.authservice.dto.response;

import com.micro.authservice.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private UserDetailsDto userDetails;
}
