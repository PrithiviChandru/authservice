package com.micro.authservice.controller;

import com.micro.authservice.dto.request.*;
import com.micro.authservice.dto.response.*;
import com.micro.authservice.schema.*;
import com.micro.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Auth APIs",
        description = "Authentication related operations"
)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register new user",
            description = "Creates a new user account with email and password"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "User registration successful",
                    content = @Content(schema = @Schema(implementation = RegisterResponseSchema.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "User already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User registration request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RegisterRequest.class))
            )
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @Operation(
            summary = "Verify user email",
            description = "Verifies a user's email using the verification token sent during registration"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Email varified successfully",
                    content = @Content(schema = @Schema(implementation = VerifyEmailResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired token",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Email already verified",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PutMapping("/verify-email")
    public ResponseEntity<ApiResponse<VerifyEmailResponse>> verifyEmail(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Verify email request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = VerifyEmailRequest.class)
                    )
            )
            @RequestBody VerifyEmailRequest request
    ) {
        return ResponseEntity.ok(authService.verifyEmail(request));
    }

    @Operation(
            summary = "Resend verification token",
            description = "Generates a new email verification token for users who have not yet verified their account"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Verification token resent successfully",
                    content = @Content(schema = @Schema(implementation = RegisterResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Email already verified",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PostMapping("/resent-verification")
    public ResponseEntity<ApiResponse<ResendVerificationResponse>> resentVerification(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Resent verification request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ResentVerificationRequest.class)
                    )

            )
            @Valid @RequestBody ResentVerificationRequest request
    ) {
        return ResponseEntity.ok(authService.resentVerification(request));
    }

    @Operation(
            summary = "User Login",
            description = "Authenticates user credentials and returns JWT access token"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseSchema.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User login credentials",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            )
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(
            summary = "User Logout",
            description = "Logs out the user by invalidating the JWT token"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Logout successful",
                    content = @Content(schema = @Schema(implementation = LogoutResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<LogoutResponse>> logout(
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity.ok(authService.logout(accessToken));
    }

    @Operation(
            summary = "Validate JWT token",
            description = "Validates the provided JWT token and returns its status"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Token validation result",
                    content = @Content(schema = @Schema(implementation = TokenValidationResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Token missing or invalid",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized (if token format is incorrect)",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/validate-token")
    public ResponseEntity<ApiResponse<TokenValidationResponse>> validateToken(
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity.ok(authService.validateToken(accessToken));
    }

    @Operation(
            summary = "Refresh JWT token",
            description = "Generates a new access token using a valid refresh token"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Token refreshed successfully",
                    content = @Content(schema = @Schema(implementation = RefreshTokenResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid refresh token",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Refresh token expired or unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Refresh token request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RefreshTokenRequest.class)
                    )
            )
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @Operation(
            summary = "Change user password",
            description = "Allows authenticated user to change password using old password"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Password changed successfully",
                    content = @Content(schema = @Schema(implementation = ChangePasswordResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or wrong old password",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - invalid or missing token",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<ChangePasswordResponse>> changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity.ok(authService.changePassword(accessToken, request));
    }

    @Operation(
            summary = "Forgot Password",
            description = "Generates a password reset token and sends it to the registered email address"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Reset link sent successfully",
                    content = @Content(schema = @Schema(implementation = ForgotPasswordResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<ForgotPasswordResponse>> forgotPassword(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User email for password reset",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ForgotPasswordRequest.class)
                    )
            )
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.forgetPassword(request));
    }

    @Operation(
            summary = "Reset Password",
            description = "Resets user password using a valid reset token"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Password reset successful",
                    content = @Content(schema = @Schema(implementation = ResetPasswordResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired token",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<ResetPasswordResponse>> resetPassword(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Reset password request payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ResetPasswordRequest.class)
                    )
            )
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }

}
