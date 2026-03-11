
package com.example.aichat.controller;

import com.example.aichat.dto.ChatRequest;
import com.example.aichat.dto.ChatResponse;
import com.example.aichat.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatService.processMessage(request);
    }
}
