package by.baby.withdrawalmicroservice.handler;

import by.baby.component.repository.ProcessedEventRepository;
import by.baby.enums.MessageType;
import by.baby.event.CreatedWithdrawalEvent;
import by.baby.withdrawalmicroservice.persistence.entity.WithdrawalEntity;
import by.baby.withdrawalmicroservice.repository.WithdrawalRepository;
import by.baby.persistence.entity.ProcessedEventEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "withdrawal-created-events-topic")
public class WithdrawalCreatedEventHandler {

    private final ProcessedEventRepository processedEventRepository;

    public WithdrawalCreatedEventHandler(WithdrawalRepository withdrawalRepository, ProcessedEventRepository processedEventRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.processedEventRepository = processedEventRepository;
    }

    Logger LOGGER = LoggerFactory.getLogger(WithdrawalCreatedEventHandler.class);

    private final WithdrawalRepository withdrawalRepository;

    @KafkaHandler
    public void handle(@Payload CreatedWithdrawalEvent withdrawalEvent,
                       @Header("messageId") String messageId) {
        LOGGER.info("Received Created Withdrawal Event: {}", withdrawalEvent);
        if (withdrawalRepository.findByMessageId(messageId) != null) {
            LOGGER.info("Message with id {} already exists", messageId);
            return;
        }

        WithdrawalEntity withdrawalEntity = new WithdrawalEntity(
                withdrawalEvent.getSenderId(),
                withdrawalEvent.getAmount(),
                messageId
        );

        withdrawalRepository.saveAndFlush(withdrawalEntity);

        ProcessedEventEntity processedEventEntity = new ProcessedEventEntity(
                false,
                MessageType.WITHDRAWAL,
                messageId
        );

        publish(processedEventEntity);

        LOGGER.info("Saved Withdrawal Entity: {}", withdrawalEntity);
    }

    void publish(ProcessedEventEntity processedEventEntity) {
        processedEventRepository.save(processedEventEntity);
    }
}
