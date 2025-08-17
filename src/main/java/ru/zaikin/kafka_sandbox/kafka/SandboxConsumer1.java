package ru.zaikin.kafka_sandbox.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SandboxConsumer1 {

    @KafkaListener(topics = "sandbox-topic", groupId = "sandbox-group")
    public void listen(String message) {
        System.out.println("Received from 1: " + message);
    }
}
