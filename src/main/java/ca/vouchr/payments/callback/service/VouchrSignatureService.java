package ca.vouchr.payments.callback.service;

import ca.vouchr.payments.callback.config.VouchrConfigurationProperties;
import ca.vouchr.payments.callback.dto.PublicKeyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@Service
public class VouchrSignatureService {

    private static RSAPublicKey publicKey = null;

    private static final Logger logger = Logger.getLogger(VouchrSignatureService.class.getName());

    @Autowired
    private VouchrConfigurationProperties vouchrConfigurationProperties;

    private synchronized void updatePublicKey() throws GeneralSecurityException, IOException {

        logger.info("retrieving public key");
        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "ApiKey " + vouchrConfigurationProperties.getSignature().getPublicKey().getApiKey());


        try {
            RequestEntity request = RequestEntity
                    .get(new URI(vouchrConfigurationProperties.getSignature().getPublicKey().getUrl()))
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", "ApiKey " + vouchrConfigurationProperties.getSignature().getPublicKey().getApiKey())
                    .build();

            ResponseEntity<PublicKeyResponse> response = restTemplate.exchange(request, PublicKeyResponse.class);

            String pemKey = response.getBody().getSignaturePublicKey();

            pemKey = pemKey.replaceFirst("-----BEGIN PUBLIC KEY-----", "");
            pemKey = pemKey.replaceFirst("-----END PUBLIC KEY-----", "");
            pemKey = pemKey.replaceAll("\\s", "");
            byte[] derKey = Base64.getDecoder().decode(pemKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(derKey);

            publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);;

            logger.info("retrieved public key from " + vouchrConfigurationProperties.getSignature().getPublicKey().getUrl());

        } catch (Exception ex) {
            throw new IOException("Error retrieving Public Key", ex);
        }
    }

    public RSAPublicKey getPublicKey() throws GeneralSecurityException, IOException {

        if (publicKey == null) {
            updatePublicKey();
        }

        return publicKey;
    }

    public boolean verify(byte[] data, byte[] signature) throws GeneralSecurityException, IOException {


        PublicKey rsaKey = getPublicKey();

        Signature rsaVerify = Signature.getInstance("SHA256withRSA");
        rsaVerify.initVerify(rsaKey);
        rsaVerify.update(data);
        return rsaVerify.verify(signature);

    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 60000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);

        return clientHttpRequestFactory;
    }

}
