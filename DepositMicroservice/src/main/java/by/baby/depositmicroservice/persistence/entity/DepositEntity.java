package by.baby.depositmicroservice.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deposit")
public class DepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int receiverId;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false, unique = true)
    private String messageId;

    public DepositEntity() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public DepositEntity(int receiverId, int amount, String messageId) {
        this.receiverId = receiverId;
        this.amount = amount;
        this.messageId = messageId;
    }

    public DepositEntity(int receiverId, int amount) {
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DepositEntity{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
