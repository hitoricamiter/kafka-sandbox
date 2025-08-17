package ru.zaikin.kafka_sandbox.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;


import java.util.HashSet;
import java.util.Set;

@Component
public class SandboxConsumer1 {

    private Set<String> processedEventIds = new HashSet<>();

    @KafkaListener(topics = "sandbox-topic", groupId = "sandbox-group")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_KEY) String eventId) {

        System.out.println("Эвент который пришел имеет айдишник -> " + eventId);
        if (processedEventIds.contains(eventId)) {
            System.out.println("Skipping duplicate event: " + eventId);
            return;
        }

        processedEventIds.add(eventId);
        System.out.println("Processing event: " + eventId + " -> " + message);
    }
}
