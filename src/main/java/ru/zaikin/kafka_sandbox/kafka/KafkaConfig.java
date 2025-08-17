package ru.zaikin.kafka_sandbox.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration // Говорит Spring, что этот класс содержит бины для контекста
@EnableKafka  // Включает поддержку Kafka, чтобы можно было использовать @KafkaListener
public class KafkaConfig {

    @Bean
    public NewTopic sandboxTopic() {
        return new NewTopic("sandbox-topic", 2, (short) 1); // 2 партиции, 1 реплика
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Адрес Kafka-брокера
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sandbox-group");           // ID группы консьюмеров
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Десериализация ключей в String
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Десериализация значений в String
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");       // Если оффсет не найден, читать с начала
        return new DefaultKafkaConsumerFactory<>(props); // Создаём фабрику консьюмеров с этими настройками
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory()); // Подключаем фабрику консьюмеров к слушателям
        return factory; // Бин, который Spring использует для @KafkaListener
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory2() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Адрес Kafka-брокера
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sandbox-group2");           // ID группы консьюмеров
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Десериализация ключей в String
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Десериализация значений в String
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");       // Если оффсет не найден, читать с начала
        return new DefaultKafkaConsumerFactory<>(props); // Создаём фабрику консьюмеров с этими настройками
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2()); // Подключаем фабрику консьюмеров к слушателям
        return factory; // Бин, который Spring использует для @KafkaListener
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Адрес Kafka-брокера
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);   // Сериализация ключей в String
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Сериализация значений в String
        return new DefaultKafkaProducerFactory<>(props); // Создаём фабрику продюсеров с этими настройками
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory()); // Шаблон для отправки сообщений в Kafka
    }
}
