package com.wilk.depositservice.handler;


import com.wilk.core.events.DepositRequestEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@KafkaListener(topics = "deposit-money-topic")
public class DepositRequestedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @KafkaHandler
    public void handle(DepositRequestEvent depositRequestEvent){
        LOGGER.info("Received a new deposit event : {}",depositRequestEvent.getAmount());
    }

}
