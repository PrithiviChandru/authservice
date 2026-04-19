package com.micro.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LogoutResponse implements Serializable {
    private boolean success;
}
