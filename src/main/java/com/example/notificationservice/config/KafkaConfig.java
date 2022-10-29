package com.example.notificationservice.config;

import com.example.notificationservice.model.Mail;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Autowired
    private Environment environment;

    @Bean
    public Map<String, Object> getConfigProps() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("kafka.bootstrap.server","127.0.0.1"));
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG,
                environment.getProperty("kafka.consumer.group.id"));
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return configMap;
    }

    @Bean
    public ConsumerFactory<String, Mail> getMailConsumer() {
        return new DefaultKafkaConsumerFactory<>(
                getConfigProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(Mail.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Mail> getKafkaConsumerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Mail> listenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(getMailConsumer());
        return listenerContainerFactory;
    }
}
