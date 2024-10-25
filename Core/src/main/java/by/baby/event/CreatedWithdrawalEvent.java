package by.baby.event;

public class CreatedWithdrawalEvent implements PaymentEvent {
    private int senderId;
    private int amount;

    public CreatedWithdrawalEvent() {
    }

    public CreatedWithdrawalEvent(int senderId, int amount) {
        this.senderId = senderId;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "CreatedWithdrawalEvent{" +
                "senderId=" + senderId +
                ", amount=" + amount +
                '}';
    }
}
