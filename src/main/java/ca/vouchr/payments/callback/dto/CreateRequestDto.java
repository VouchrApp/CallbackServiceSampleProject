package ca.vouchr.payments.callback.dto;

import java.math.BigDecimal;

public class CreateRequestDto {

    private String voucherId;
    private UserDto sender;
    private UserDto recipient;
    private CreateRequestPaymentDto payment = new CreateRequestPaymentDto();
    private BigDecimal amount;
    private String currency;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

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

    public CreateRequestPaymentDto getPayment() {
        return payment;
    }

    public void setPayment(CreateRequestPaymentDto payment) {
        this.payment = payment;
    }

    public static class CreateRequestPaymentDto {
        private PaymentSourceDto source;

        public PaymentSourceDto getSource() {
            return source;
        }

        public void setSource(PaymentSourceDto source) {
            this.source = source;
        }

    }

}
