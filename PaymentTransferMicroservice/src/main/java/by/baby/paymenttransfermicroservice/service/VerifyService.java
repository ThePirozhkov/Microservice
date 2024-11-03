package by.baby.paymenttransfermicroservice.service;

import by.baby.component.repository.ProcessedEventRepository;
import by.baby.dto.TransferResponse;
import by.baby.persistence.entity.ProcessedEventEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class VerifyService {
    private final ProcessedEventRepository processedEventRepository;

    public VerifyService(ProcessedEventRepository processedEventRepository) {
        this.processedEventRepository = processedEventRepository;
    }

    public boolean verify(TransferResponse transferResponse) {

        int counter = 0;
        while (!processedEventRepository.existsByMessageId(transferResponse.getWithdrawalMessageId())
        && !processedEventRepository.existsByMessageId(transferResponse.getDepositMessageId())
        && counter <= 10) {
            try {
                Thread.sleep(1000);
                counter++;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        ProcessedEventEntity processedDepositEventEntity =
                processedEventRepository.findByMessageId(transferResponse.getDepositMessageId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProcessedEventEntity processedWithdrawalEventEntity =
                processedEventRepository.findByMessageId(transferResponse.getWithdrawalMessageId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        processedDepositEventEntity.setVerified(true);
        processedWithdrawalEventEntity.setVerified(true);
        processedEventRepository.save(processedDepositEventEntity);
        processedEventRepository.save(processedWithdrawalEventEntity);
        return true;
    }
}
