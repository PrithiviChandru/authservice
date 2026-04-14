package com.micro.authservice.repository;

import com.micro.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String token);

    Optional<User> findByVerificationToken(String verificationToken);

    Optional<User> findByRefreshToken(String refreshToken);
}
