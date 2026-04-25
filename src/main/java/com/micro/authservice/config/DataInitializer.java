package com.micro.authservice.config;

import com.micro.authservice.entity.User;
import com.micro.authservice.enums.Role;
import com.micro.authservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                User admin = User.builder()
                        .firstName("Super")
                        .lastName("Admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(Role.ADMIN)
                        .timeZone("Asia/Kolkata")
                        .verified(true)
                        .createdAt(Instant.now())
                        .build();

                userRepository.save(admin);
                System.out.println("ADMIN created");
            }
        };
    }
}
