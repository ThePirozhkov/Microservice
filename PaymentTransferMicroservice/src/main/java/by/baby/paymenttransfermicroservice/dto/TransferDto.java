package by.baby.paymenttransfermicroservice.dto;

public class TransferDto {
    private int senderId;
    private int receiverId;
    private int amount;

    public TransferDto() {
    }

    public TransferDto(int senderId, int receiverId, int amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
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
        return "TransferDto{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                '}';
    }
}
