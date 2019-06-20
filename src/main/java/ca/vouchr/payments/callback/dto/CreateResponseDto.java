package ca.vouchr.payments.callback.dto;

public class CreateResponseDto {

    private CreateResponsePaymentDto payment = new CreateResponsePaymentDto();

    public CreateResponsePaymentDto getPayment() {
        return payment;
    }

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
