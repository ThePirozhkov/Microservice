package by.baby.component.repository;

import by.baby.persistence.entity.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long> {
    Optional<ProcessedEventEntity> findByMessageId(String messageId);
    boolean existsByMessageId(String messageId);
}
