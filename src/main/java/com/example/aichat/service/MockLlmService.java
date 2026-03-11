
package com.example.aichat.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MockLlmService {

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public String generateResponse(String message) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }

        if (message.contains("fail")) {
            throw new RuntimeException("Mock LLM failure");
        }

        return "AI Response to: " + message;
    }
}
