package com.wilk.transferservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRestModel {

    private String senderId;
    private String recepientId;
    private BigDecimal amount;


}
