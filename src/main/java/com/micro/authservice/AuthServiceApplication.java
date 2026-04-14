package com.micro.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class AuthServiceApplication {

    /*
     *  JWT Token Explanation
     * */
    private static void jwtDecode() {
        String token =      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcml0aHZpQGdtYWlsLmNvbSIsImlhdCI6MTc3NTY1NTQ5NywiZXhwIjoxNzc1NjU1NTA3fQ.pZY-btXFr6SE03v8UdjraB5pI4GPutSt80Z7_6gNly8"
        ;
        String[] parts = token.split("\\.");

        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        String signature = parts[2];

        System.out.println(header);
        System.out.println(payload);
        System.out.println(signature);
    }

    public static void main(String[] args) {
//        jwtDecode();
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
