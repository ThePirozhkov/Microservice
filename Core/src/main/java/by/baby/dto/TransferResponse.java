package by.baby.dto;

public class TransferResponse {
    private String depositMessageId;
    private String withdrawalMessageId;

    public TransferResponse(String depositMessageId, String withdrawalMessageId) {
        this.depositMessageId = depositMessageId;
        this.withdrawalMessageId = withdrawalMessageId;
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
}
