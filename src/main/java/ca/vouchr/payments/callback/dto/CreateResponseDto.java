package ca.vouchr.payments.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateResponseDto {

    private CreateResponsePaymentDto payment = new CreateResponsePaymentDto();

    public CreateResponsePaymentDto getPayment() {
        return payment;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateResponsePaymentDto {
        private PaymentSourceDto source;
        private PaymentDestDto dest;

        public PaymentSourceDto getSource() {
            return source;
        }

        public void setSource(PaymentSourceDto source) {
            this.source = source;
        }

        public PaymentDestDto getDest() {
            return dest;
        }

        public void setDest(PaymentDestDto dest) {
            this.dest = dest;
        }
    }

}
