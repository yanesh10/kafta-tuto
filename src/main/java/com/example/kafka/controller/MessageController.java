package com.example.kafka.controller;

import com.example.kafka.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<Boolean> sendMessage(@RequestParam String message) {
        messageService.sendMessage(message);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
