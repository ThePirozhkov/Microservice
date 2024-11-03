package by.baby.paymenttransfermicroservice.repository;

import by.baby.paymenttransfermicroservice.persistence.entity.PaymentTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransferRepository extends JpaRepository<PaymentTransferEntity, Long> {
}
