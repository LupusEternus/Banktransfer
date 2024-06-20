package com.wilk.transferservice.service;

import com.wilk.transferservice.model.TransferRestModel;

public interface TransferService {

    boolean transfer(TransferRestModel transferRestModel);

}
