package ru.zaikin.kafka_sandbox.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SandboxProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String eventId, String message) {
        kafkaTemplate.send("sandbox-topic", eventId, message);
        System.out.println("Sent event: " + eventId + " -> " + message);
    }
}
