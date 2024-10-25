package by.baby.withdrawalmicroservice.repository;

import by.baby.persistence.entity.WithdrawalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<WithdrawalEntity, Long> {
    WithdrawalEntity findByMessageId(String messageId);
}
