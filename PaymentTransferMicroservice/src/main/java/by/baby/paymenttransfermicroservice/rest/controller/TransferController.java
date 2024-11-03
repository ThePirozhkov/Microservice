package by.baby.paymenttransfermicroservice.rest.controller;

import by.baby.dto.TransferResponse;
import by.baby.paymenttransfermicroservice.dto.TransferDto;
import by.baby.paymenttransfermicroservice.service.TransferService;
import by.baby.paymenttransfermicroservice.service.VerifyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final VerifyService verifyService;

    public TransferController(TransferService transferService, VerifyService verifyService) {
        this.transferService = transferService;
        this.verifyService = verifyService;
    }

    private final TransferService transferService;

    @PostMapping
    public String transfer(@RequestBody TransferDto transferDto) throws ExecutionException, InterruptedException, TimeoutException {

        TransferResponse transferResponse = transferService.transfer(transferDto);
        boolean result;

        if (transferResponse == null) {
            return "Operation failed";
        } else {
            result = verifyService.verify(transferResponse);
        }


        if (!result) {
            return "Operation failed";
        } else {
            return "Transfer successful";
        }
    }

}
