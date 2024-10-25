package by.baby.depositmicroservice.handler;

import by.baby.persistence.entity.DepositEntity;
import by.baby.depositmicroservice.repository.DepositRepository;
import by.baby.event.CreatedDepositEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "deposit-created-events-topic")
public class CreatedDepositEventHandler {

    Logger LOGGER = LoggerFactory.getLogger(CreatedDepositEventHandler.class);

    public CreatedDepositEventHandler(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    private final DepositRepository depositRepository;

    @KafkaHandler
    public void handle(@Payload CreatedDepositEvent createdDepositEvent,
                       @Header("messageId") String messageId) {

        LOGGER.info("Received Created Deposit Event: {}", createdDepositEvent);
        if (depositRepository.findByMessageId(messageId) != null) {
            LOGGER.info("Message with id {} already exists", messageId);
            return;
        }

        DepositEntity depositEntity = new DepositEntity(
                createdDepositEvent.getReceiverId(),
                createdDepositEvent.getAmount(),
                messageId
        );

        depositRepository.saveAndFlush(depositEntity);

        LOGGER.info("Saved Deposit Entity: {}", depositEntity);
    }

}
