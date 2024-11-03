package by.baby.paymenttransfermicroservice.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment_transfer")
public class PaymentTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String withdrawalMessageId;

    @Column(nullable = false)
    private String depositMessageId;

    public Date createdAt;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = new Date();
    }

    public PaymentTransferEntity() {
    }

    public String getDepositMessageId() {
        return depositMessageId;
    }

    public void setDepositMessageId(String depositMessageId) {
        this.depositMessageId = depositMessageId;
    }

    public String getWithdrawalMessageId() {
        return withdrawalMessageId;
    }

    public void setWithdrawalMessageId(String withdrawalMessageId) {
        this.withdrawalMessageId = withdrawalMessageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentTransferEntity(String withdrawalMessageId, String depositMessageId) {
        this.withdrawalMessageId = withdrawalMessageId;
        this.depositMessageId = depositMessageId;
    }
}
