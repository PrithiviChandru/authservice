package com.micro.authservice.entity;

import com.micro.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    // 🔹 1. Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 2. Basic Info
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String timeZone;

    // 🔹 3. Authentication
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 🔹 4. Verification
    private boolean verified;

    private String verificationToken;

    private LocalDateTime verificationTokenExpiry;

    // 🔹 5. Password Reset
    private String resetToken;

    private LocalDateTime resetTokenExpiry;

    // 🔹 6. Refresh Token
    private String refreshToken;

    private LocalDateTime refreshTokenExpiry;

    // 🔹 7. Audit Fields
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}