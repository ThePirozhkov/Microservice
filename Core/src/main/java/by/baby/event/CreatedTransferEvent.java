package by.baby.event;

public class CreatedTransferEvent implements PaymentEvent {
    private int senderId;
    private int receiverId;
    private int amount;

    public CreatedTransferEvent() {
    }

    public CreatedTransferEvent(int senderId, int receiverId, int amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "CreatedTransferEvent{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                '}';
    }
}
