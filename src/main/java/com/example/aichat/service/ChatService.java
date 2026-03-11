package com.example.aichat.service;

import com.example.aichat.dto.ChatRequest;
import com.example.aichat.dto.ChatResponse;
import com.example.aichat.entity.Message;
import com.example.aichat.repository.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {   // ← class must exist

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final MockLlmService llmService;
    private final MessageRepository repository;

    public ChatService(MockLlmService llmService, MessageRepository repository) {
        this.llmService = llmService;
        this.repository = repository;
    }

    public ChatResponse processMessage(ChatRequest request) {

        repository.findByRequestId(request.getRequestId())
                .ifPresent(existing -> {
                    throw new RuntimeException("Duplicate request detected");
                });

        log.info("Processing message from user {}", request.getUserId());

        Message userMsg = new Message();
        userMsg.setUserId(request.getUserId());
        userMsg.setConversationId(request.getConversationId());
        userMsg.setRole("USER");
        userMsg.setContent(request.getMessage());
        userMsg.setRequestId(request.getRequestId());
        userMsg.setTimestamp(LocalDateTime.now());

        repository.save(userMsg);

        String aiResponse = llmService.generateResponse(request.getMessage());

        Message aiMsg = new Message();
        aiMsg.setUserId(request.getUserId());
        aiMsg.setConversationId(request.getConversationId());
        aiMsg.setRole("AI");
        aiMsg.setContent(aiResponse);
        aiMsg.setTimestamp(LocalDateTime.now());

        repository.save(aiMsg);

        return new ChatResponse(aiResponse);
    }
}