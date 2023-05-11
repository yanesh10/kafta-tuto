package com.example.kafka.service;

import com.example.kafka.model.Greeting;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    public static final String KAFKA_DEMO = "kafka-demo";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    public void sendMessage(String msg) {
        log.info("Sending Message");
        kafkaTemplate.send(KAFKA_DEMO, msg);
    }

    public void sendMessage(Greeting greeting){
        log.info("Sending Greeting Message");
        greetingKafkaTemplate.send(KAFKA_DEMO, greeting);
    }

    @KafkaListener(topics = KAFKA_DEMO)
    public void listenToMessage(String message) {
        log.info("Received message: {}", message);
    }

    @KafkaListener(topics = KAFKA_DEMO, groupId = "demoGrp")
    public void listenToMessageWithGroup(String message) {
        log.info("Received message for group demoGrp: {}", message);
    }

//    @KafkaListener(topics = KAFKA_DEMO)
//    public void listenToMessageWithHeader(@Payload String message,
//                                          @Header(KafkaHeaders.GROUP_ID) String groupId, @Header(KafkaHeaders.RECEIVED_PARTITION) String partition) {
//        log.info("Received message for group {}: {}", groupId, message);
//        log.info("Partition id for message: {}", partition);
//    }

    @KafkaListener(topics = KAFKA_DEMO, containerFactory = "kafkaFilterListenerContainerFactory")
    public void listenToMagicMessage(String message) {
        log.info("Received magic message: {}", message);
    }

    @KafkaListener(topics = KAFKA_DEMO, containerFactory = "greetingConcurrentKafkaListenerContainerFactory")
    public void listenToGreetingMessage(Greeting greeting){
        log.info("Received greeting message {} with name {}", greeting.getMsg(), greeting.getName());
    }
}
