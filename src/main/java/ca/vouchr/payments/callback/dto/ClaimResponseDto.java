package ca.vouchr.payments.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimResponseDto {

    private ClaimResponsePaymentDto payment = new ClaimResponsePaymentDto();

    public ClaimResponsePaymentDto getPayment() {
        return payment;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ClaimResponsePaymentDto {
        private PaymentDestDto dest;
        private PaymentRedeemDto redeem;

        public PaymentDestDto getDest() {
            return dest;
        }

        public void setDest(PaymentDestDto dest) {
            this.dest = dest;
        }

        public PaymentRedeemDto getRedeem() {
            return redeem;
        }

        public void setRedeem(PaymentRedeemDto redeem) {
            this.redeem = redeem;
        }
    }

}