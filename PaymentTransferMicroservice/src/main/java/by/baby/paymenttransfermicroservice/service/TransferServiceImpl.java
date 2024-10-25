package by.baby.paymenttransfermicroservice.service;

import by.baby.event.CreatedDepositEvent;
import by.baby.event.CreatedWithdrawalEvent;
import by.baby.event.PaymentEvent;
import by.baby.paymenttransfermicroservice.dto.TransferDto;
import by.baby.paymenttransfermicroservice.persistance.entity.PaymentTransferEntity;
import by.baby.paymenttransfermicroservice.repository.PaymentTransferRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private final PaymentTransferRepository paymentTransferRepository;

    public TransferServiceImpl(PaymentTransferRepository paymentTransferRepository,
                               KafkaTemplate<String, PaymentEvent> paymentEventKafkaTemplate,
                               RestTemplate restTemplate) {
        this.paymentTransferRepository = paymentTransferRepository;
        this.paymentKafkaTemplate = paymentEventKafkaTemplate;
        this.restTemplate = restTemplate;
    }

    private final KafkaTemplate<String, PaymentEvent> paymentKafkaTemplate;
    private final RestTemplate restTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String transfer(TransferDto transferDto)  {
        try {
            LOGGER.info("Transfer started with data: {}", transferDto);
            CreatedDepositEvent depositEvent = new CreatedDepositEvent(
                    transferDto.getReceiverId(),
                    transferDto.getAmount()
            );
            CreatedWithdrawalEvent withdrawalEvent = new CreatedWithdrawalEvent(
                    transferDto.getSenderId(),
                    transferDto.getAmount()
            );

            ProducerRecord<String, PaymentEvent> depositRecord = new ProducerRecord<>(
                    "deposit-created-events-topic",
                    UUID.randomUUID().toString(),
                    depositEvent
            );
            ProducerRecord<String, PaymentEvent> withdrawalRecord = new ProducerRecord<>(
                    "withdrawal-created-events-topic",
                    UUID.randomUUID().toString(),
                    withdrawalEvent
            );

            String depositMessageId = UUID.randomUUID().toString();
            String withdrawalMessageId = UUID.randomUUID().toString();
            depositRecord.headers().add("messageId", depositMessageId.getBytes());
            withdrawalRecord.headers().add("messageId", withdrawalMessageId.getBytes());

            paymentKafkaTemplate.send(depositRecord)
                    .whenComplete((result, error) -> {
                        if (error != null) {
                            LOGGER.error("TransferError (Deposit): {}", error.getMessage());
                            throw new RuntimeException("TransferError (Deposit): " + error.getMessage());
                        } else {
                            LOGGER.info("DepositEntity processed successfully with key {}", depositMessageId);
                        }
                    });

            restTemplate.exchange("http://localhost:8080/mock", HttpMethod.POST, null, String.class);

            paymentKafkaTemplate.send(withdrawalRecord)
                            .whenComplete((result, error) -> {
                                if (error != null) {
                                    LOGGER.error("TransferError (Withdrawal): {}", error.getMessage());
                                    throw new RuntimeException("TransferError (Withdrawal): " + error.getMessage());
                                } else {
                                    LOGGER.info("WithdrawalEntity processed successfully with key {}", withdrawalMessageId);
                                }
                            });

            PaymentTransferEntity paymentTransferEntity = new PaymentTransferEntity(
                    withdrawalMessageId,
                    depositMessageId
            );

            paymentTransferRepository.save(paymentTransferEntity);
        } catch (Exception e) {
            LOGGER.error("Transfer service | Exception : {}", e.getMessage());
            throw e;
        }
        String result = String.format("Successfully transfer from %s to %s ($%s)",
                transferDto.getSenderId(), transferDto.getReceiverId(), transferDto.getAmount());
        LOGGER.info(result);
        return result;
    }

}
