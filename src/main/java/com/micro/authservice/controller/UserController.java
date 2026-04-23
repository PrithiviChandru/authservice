package com.micro.authservice.controller;

import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.dto.request.auth.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.user.DeleteResponse;
import com.micro.authservice.schema.ErrorResponseSchema;
import com.micro.authservice.schema.user.DeleteResponseSchema;
import com.micro.authservice.schema.user.UserDetailsListSchema;
import com.micro.authservice.schema.user.UserDetailsSchema;
import com.micro.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Update user profile",
            description = "Updates the authenticated user's profile details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Profile updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDetailsSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserDetailsDto>> updateProfile(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Reset password request payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UpdateProfileRequest.class)
                    )
            )
            @RequestBody UpdateProfileRequest request,
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity.ok(userService.updateProfile(accessToken, request));
    }

    @Operation(
            summary = "Get all users",
            description = "Fetches a  list of users"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Users fetched successfully",
                    content = @Content(schema = @Schema(implementation = UserDetailsListSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserDetailsDto>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(
            summary = "Get user by ID",
            description = "Fetches user details by user ID (Admin or owner access)"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User fetched successfully",
                    content = @Content(schema = @Schema(implementation = UserDetailsSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserDetailsDto>> getUserById(
            @Parameter(
                    description = "User ID",
                    required = true,
                    example = "1"
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(
            summary = "Delete user",
            description = "Deletes a user by ID (Admin only)"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully",
                    content = @Content(schema = @Schema(implementation = DeleteResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<DeleteResponse>> deleteById(
            @Parameter(
                    description = "User ID",
                    required = true,
                    example = "1"
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
