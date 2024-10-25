package by.baby.paymenttransfermicroservice.rest.controller;

import by.baby.paymenttransfermicroservice.dto.TransferDto;
import by.baby.paymenttransfermicroservice.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    private final TransferService transferService;

    @PostMapping
    public String transfer(@RequestBody TransferDto transferDto) throws ExecutionException, InterruptedException, TimeoutException {
        return transferService.transfer(transferDto);
    }

}
