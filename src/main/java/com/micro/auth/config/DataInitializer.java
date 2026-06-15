package com.micro.auth.config;

import com.micro.auth.entity.User;
import com.micro.auth.enums.Role;
import com.micro.auth.repository.UserRepository;
import com.micro.category.entity.Category;
import com.micro.category.repository.CategoryRepository;
import com.micro.product.entity.Product;
import com.micro.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
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

            List<String> cNames = Arrays.asList("Electronics", "Fashions");
            cNames.forEach(cName -> {
                if (!categoryRepository.existsByNameIgnoreCase(cName)) {
                    Category category = Category.builder()
                            .name(cName)
                            .description(cName + " description sample")
                            .build();
                    categoryRepository.save(category);
                }
            });
            System.out.println("Categories created");

            List<String> pNames = Arrays.asList("Wireless Mouse", "Shirt");
            Random random = new Random();
            int priceMin = 300, priceMax = 700;
            int stockMin = 10, stockMax = 25;
            AtomicInteger cId = new AtomicInteger(1);
            pNames.forEach(pName -> {
                if (productRepository.findByName(pName).isEmpty()) {
                    Product product = Product.builder()
                            .name(pName)
                            .description(pName + " description sample")
                            .category(categoryRepository.findById(Long.valueOf(cId.getAndIncrement())).get())
                            .price(BigDecimal.valueOf(random.nextInt(priceMax - priceMin + 1) + priceMin))
                            .stock(random.nextInt(stockMax - stockMin + 1) + stockMin)
                            .build();
                    productRepository.save(product);
                }
            });
            System.out.println("Products created");
        };
    }
}
