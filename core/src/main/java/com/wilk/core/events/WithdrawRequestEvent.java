package com.wilk.core.events;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class WithdrawRequestEvent {

    private String senderId;
    private String recepientId;
    private BigDecimal amount;

}
