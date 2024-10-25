package by.baby.paymenttransfermicroservice.repository;

import by.baby.paymenttransfermicroservice.persistance.entity.PaymentTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransferRepository extends JpaRepository<PaymentTransferEntity, Long> {
}
