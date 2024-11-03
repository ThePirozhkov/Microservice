package by.baby.paymenttransfermicroservice.service;

import by.baby.dto.TransferResponse;
import by.baby.paymenttransfermicroservice.dto.TransferDto;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface TransferService {
    TransferResponse transfer(TransferDto transferDto) throws ExecutionException, InterruptedException, TimeoutException;
}
