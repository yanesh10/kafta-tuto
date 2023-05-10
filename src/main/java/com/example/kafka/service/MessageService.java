package com.example.kafka.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        log.info("Sending Message");
        kafkaTemplate.send("kafka-demo", msg);
    }

    @KafkaListener(topics = "kafka-demo")
    public void listenToMessage(String message) {
        log.info("Received message: {}", message);
    }
}
