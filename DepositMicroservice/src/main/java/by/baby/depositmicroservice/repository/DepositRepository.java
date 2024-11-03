package by.baby.depositmicroservice.repository;

import by.baby.depositmicroservice.persistence.entity.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<DepositEntity, Long> {
    DepositEntity findByMessageId(String messageId);
}
