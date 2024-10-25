package by.baby.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "withdrawal")
public class WithdrawalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int senderId;
    private int amount;
    private String messageId;

    public WithdrawalEntity() {
    }

    public WithdrawalEntity(int senderId, int amount, String messageId) {
        this.senderId = senderId;
        this.amount = amount;
        this.messageId = messageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "WithdrawalEntity{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", amount=" + amount +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
