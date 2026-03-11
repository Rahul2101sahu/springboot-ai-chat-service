
package com.example.aichat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class AiChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiChatApplication.class, args);
    }
}
