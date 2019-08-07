package ca.vouchr.payments.callback.dto;

import java.math.BigDecimal;

public class CreateRequestDto {

    private String voucherId;
    private UserDto sender;
    private UserDto recipient;
    private CallbackSourcePaymentDto payment = new CallbackSourcePaymentDto();
    private BigDecimal amount;
    private String currency;
    private String ipAddress;
    private ShareUrlDto claimUrl;

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

    public CallbackSourcePaymentDto getPayment() {
        return payment;
    }

    public void setPayment(CallbackSourcePaymentDto payment) {
        this.payment = payment;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ShareUrlDto getClaimUrl() {
        return claimUrl;
    }

    public void setClaimUrl(ShareUrlDto claimUrl) {
        this.claimUrl = claimUrl;
    }

    public static class CallbackSourcePaymentDto {
        private PaymentSourceDto source;

        public PaymentSourceDto getSource() {
            return source;
        }

        public void setSource(PaymentSourceDto source) {
            this.source = source;
        }

    }

}
