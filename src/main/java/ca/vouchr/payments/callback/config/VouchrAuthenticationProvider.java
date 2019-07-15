package ca.vouchr.payments.callback.config;

import ca.vouchr.payments.callback.service.VouchrSignatureService;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VouchrAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(VouchrAuthenticationProvider.class.getName());


    @Autowired
    VouchrSignatureService vouchrSignatureService;

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {

        logger.info("authenticating");
        try {

            VouchrPreAuthentication vouchrPreAuthentication = (VouchrPreAuthentication) auth;
            RSAPublicKey publicKey = (RSAPublicKey) vouchrSignatureService.getPublicKey();

            logger.info("" + auth.getCredentials());

            SignedJWT jwt = (SignedJWT) JWTParser.parse((String) auth.getCredentials());
            if (jwt.verify(new RSASSAVerifier(publicKey))) {

                JWTClaimsSet claims = jwt.getJWTClaimsSet();

                JWTClaimsSetVerifier claimsVerifier = new DefaultJWTClaimsVerifier();
                claimsVerifier.verify(claims, null);


                VouchrAuthentication authToken = new VouchrAuthentication(claims);
                if (vouchrSignatureService.verify(vouchrPreAuthentication.getBodyContent(), vouchrPreAuthentication.getBodySignature())) {


                    authToken.setAuthenticated(true);

                    return authToken;
                } else {
                    logger.warning("VOUCHR AUTH: vouchr body signature invalid");
                }


            } else {
                logger.warning("VOUCHR AUTH: vouchr jwt signature invalid");
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "VOUCHR AUTH: vouchr jwt invalid {0}", ex.getMessage());

        }

        throw new BadCredentialsException("invalid authentication");

    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(VouchrPreAuthentication.class);
    }
}

