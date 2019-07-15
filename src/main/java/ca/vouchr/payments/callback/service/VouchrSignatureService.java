package ca.vouchr.payments.callback.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;

public interface VouchrSignatureService {

    RSAPublicKey getPublicKey() throws GeneralSecurityException, IOException;

    boolean verify(byte[] data, byte[] signature) throws GeneralSecurityException, IOException;


}
