package com.wilk.transferservice.service;

import com.wilk.core.events.DepositRequestEvent;
import com.wilk.core.events.WithdrawRequestEvent;
import com.wilk.transferservice.model.TransferRestModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransferServiceImpl implements  TransferService{

    private KafkaTemplate<String,Object> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());



    @Override
    public boolean transfer(TransferRestModel transferRestModel) {

        DepositRequestEvent depositRequestEvent = DepositRequestEvent.builder()
                .senderId(transferRestModel.getSenderId())
                .recepientId(transferRestModel.getRecepientId())
                .amount(transferRestModel.getAmount())
                .build();

        WithdrawRequestEvent withdrawRequestEvent = WithdrawRequestEvent.builder()
                .senderId(transferRestModel.getSenderId())
                .recepientId(transferRestModel.getRecepientId())
                .amount(transferRestModel.getAmount())
                .build();

        try {
            kafkaTemplate.send("withdraw-money-topic",withdrawRequestEvent);
            LOGGER.info("Sent event to withdraw topic");

            kafkaTemplate.send("deposit-money-topic",depositRequestEvent);
            LOGGER.info("Sent event to deposit topic");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
        return  true;
    }
}
