package ru.zaikin.kafka_sandbox.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SandboxConsumer2 {

    @KafkaListener(topics = "sandbox-topic", groupId = "sandbox-group")
    public void listen(String message) {
        System.out.println("Received from 2: " + message);
    }
}
