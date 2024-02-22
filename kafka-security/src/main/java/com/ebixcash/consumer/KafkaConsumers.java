package com.ebixcash.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumers {
    public static void main(String[] args) {
        // Kafka consumer configuration
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9094"); // Use SASL_SSL port
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("ssl.truststore.location", "/path/to/client.truststore");
        props.put("ssl.truststore.password", "truststore_password");
        props.put("group.id", "test-group");

        // Create Kafka consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList("test-topic"));

        // Poll for messages
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                System.out.printf("Received message: key=%s, value=%s%n", record.key(), record.value());
            });
        }
    }
}
