package by.baby.event;

public class CreatedDepositEvent implements PaymentEvent {
    private int receiverId;
    private int amount;

    public CreatedDepositEvent() {
    }

    public CreatedDepositEvent(int receiverId, int amount) {
        this.receiverId = receiverId;
        this.amount = amount;
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
        return "CreatedDepositEvent{" +
                "receiverId='" + receiverId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
