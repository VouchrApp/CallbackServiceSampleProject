package ca.vouchr.payments.callback.dto;

import java.math.BigDecimal;

public class ClaimRequestDto {

    private String voucherId;
    private UserDto sender;
    private UserDto recipient;
    private ClaimRequestPaymentDto payment = new ClaimRequestPaymentDto();
    private BigDecimal amount;
    private String currency;

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public ClaimRequestPaymentDto getPayment() {
        return payment;
    }

    public void setPayment(ClaimRequestPaymentDto payment) {
        this.payment = payment;
    }


    public static class ClaimRequestPaymentDto {
        private PaymentDestDto dest;

        public PaymentDestDto getDest() {
            return dest;
        }

        public void setDest(PaymentDestDto dest) {
            this.dest = dest;
        }
    }

}
