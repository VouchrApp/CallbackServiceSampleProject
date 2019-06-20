package ca.vouchr.payments.callback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("vouchr")
public class VouchrConfigurationProperties {

    private SignatureProperties signature;

    public SignatureProperties getSignature() {
        return signature;
    }

    public void setSignature(SignatureProperties signature) {
        this.signature = signature;
    }


    public static class SignatureProperties {
        private PublicKeyProperties publicKey;


        public PublicKeyProperties getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(PublicKeyProperties publicKey) {
            this.publicKey = publicKey;
        }
    }

    public static class PublicKeyProperties {
        private String apiKey;
        private String url;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
