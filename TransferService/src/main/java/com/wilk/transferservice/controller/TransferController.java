package com.wilk.transferservice.controller;


import com.wilk.transferservice.model.TransferRestModel;
import com.wilk.transferservice.service.TransferServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transfer")
public class TransferController {


    private TransferServiceImpl transferService;
    private final Logger LOGGER  = LoggerFactory.getLogger(this.getClass());



    @PostMapping
    public ResponseEntity<Boolean> transfer(@RequestBody TransferRestModel transferRestModel){
        Boolean transfer = transferService.transfer(transferRestModel);
        LOGGER.info("Transfer money amount: {} initiated", transferRestModel.getAmount());
        return ResponseEntity.ok(transfer);
    }

}
