package com.wilk.transferservice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("withdraw-money-topic")
    private String withdrawTopicName;
    @Value("deposit-money-topic")
    private String depositTopicName;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrap;

    @Bean
    public NewTopic createWithdrawTopic(){
        return TopicBuilder.name(withdrawTopicName).partitions(3).replicas(3).build();
    }

    @Bean
    public NewTopic createDepositTopic(){
        return TopicBuilder.name(depositTopicName).partitions(3).replicas(3).build();
    }

    @Bean
    public Map<String,Object> producerConfig(){
        Map<String,Object> config =  new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrap);
        config.put(ProducerConfig.ACKS_CONFIG,"all");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,5);
        config.put(ProducerConfig.LINGER_MS_CONFIG,0);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,3000);
        return config;
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return  new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }









}
