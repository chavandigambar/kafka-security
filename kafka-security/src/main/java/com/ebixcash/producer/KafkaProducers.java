package com.ebixcash.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducers {
    public static void main(String[] args) {
        // Kafka producer configuration
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9094"); // Use SASL_SSL port
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("ssl.truststore.location", "/path/to/client.truststore");
        props.put("ssl.truststore.password", "truststore_password");

        // Serializer configuration
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // Create Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Send a message to the topic
        ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "Hello, Kafka!");
        producer.send(record);

        // Close the producer
        producer.close();
    }
}
