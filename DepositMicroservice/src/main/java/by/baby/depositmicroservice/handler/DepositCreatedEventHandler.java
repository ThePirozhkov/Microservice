package by.baby.depositmicroservice.handler;

import by.baby.component.repository.ProcessedEventRepository;
import by.baby.depositmicroservice.persistence.entity.DepositEntity;
import by.baby.enums.MessageType;
import by.baby.event.CreatedDepositEvent;
import by.baby.depositmicroservice.repository.DepositRepository;
import by.baby.persistence.entity.ProcessedEventEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "deposit-created-events-topic")
public class DepositCreatedEventHandler {

    private final ProcessedEventRepository processedEventRepository;

    public DepositCreatedEventHandler(DepositRepository depositRepository, ProcessedEventRepository processedEventRepository) {
        this.depositRepository = depositRepository;
        this.processedEventRepository = processedEventRepository;
    }

    Logger LOGGER = LoggerFactory.getLogger(DepositCreatedEventHandler.class);

    private final DepositRepository depositRepository;

    @KafkaHandler
    public void handle(@Payload CreatedDepositEvent depositEvent,
                       @Header("messageId") String messageId) {
        LOGGER.info("Received Created Deposit Event: {}", depositEvent);
        if (depositRepository.findByMessageId(messageId) != null) {
            LOGGER.info("Message with id {} already exists", messageId);
            return;
        }

        DepositEntity depositEntity = new DepositEntity(
                depositEvent.getReceiverId(),
                depositEvent.getAmount(),
                messageId
        );

        depositRepository.saveAndFlush(depositEntity);

        ProcessedEventEntity processedEventEntity = new ProcessedEventEntity(
                false,
                MessageType.DEPOSIT,
                messageId
        );

        publish(processedEventEntity);

        LOGGER.info("Saved Deposit Entity: {}", depositEntity);
    }

    void publish(ProcessedEventEntity processedEventEntity) {
        processedEventRepository.saveAndFlush(processedEventEntity);
    }
}
