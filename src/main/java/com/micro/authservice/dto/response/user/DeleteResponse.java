package com.micro.authservice.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteResponse {
    private boolean success;
    private Long userId;
    private String message;
}
