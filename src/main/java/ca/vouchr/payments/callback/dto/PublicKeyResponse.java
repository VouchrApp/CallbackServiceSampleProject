package ca.vouchr.payments.callback.dto;

public class PublicKeyResponse {

    private String signaturePublicKey;

    public String getSignaturePublicKey() {
        return signaturePublicKey;
    }

    public void setSignaturePublicKey(String signaturePublicKey) {
        this.signaturePublicKey = signaturePublicKey;
    }
}
