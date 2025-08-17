package ru.zaikin.kafka_sandbox.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "sandbox-topic", groupId = "sandbox-group2")
    public void listen(String message) {
        System.out.println("Received from another consumer group: " + message);
    }
}