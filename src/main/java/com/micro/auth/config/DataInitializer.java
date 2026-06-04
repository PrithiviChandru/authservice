package com.micro.auth.config;

import com.micro.auth.entity.User;
import com.micro.auth.enums.Role;
import com.micro.auth.repository.UserRepository;
import com.micro.product.entity.Product;
import com.micro.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(
            UserRepository userRepository,
            ProductRepository productRepository,
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

            if (productRepository.findByName("Wireless Mouse").isEmpty()) {
                List<Product> products = List.of(
                        Product.builder()
                                .name("Wireless Mouse")
                                .description("Bluetooth rechargeable mouse")
                                .price(BigDecimal.valueOf(799.00))
                                .stock(25)
                                .build(),
                        Product.builder()
                                .name("Keyboard")
                                .description("Keyboard")
                                .price(BigDecimal.valueOf(299.00))
                                .stock(10)
                                .build(),
                        Product.builder()
                                .name("Monitor")
                                .description("Monitor")
                                .price(BigDecimal.valueOf(2999.00))
                                .stock(5)
                                .build()
                );
//                products.forEach(p -> productRepository.save(p));
//                System.out.println("Products created");
            }
        };
    }
}
