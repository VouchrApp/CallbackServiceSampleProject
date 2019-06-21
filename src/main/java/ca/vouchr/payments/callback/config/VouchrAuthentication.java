package ca.vouchr.payments.callback.config;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VouchrAuthentication implements Authentication {
    private static final Logger logger = Logger.getLogger(VouchrAuthentication.class.getName());

    private String jwtToken;
    private VouchrPrincipal principal;
    private boolean authenticated;


    public VouchrAuthentication() {

    }

    public VouchrAuthentication(JWTClaimsSet claims) {
        this.jwtToken = jwtToken;
        this.principal = principal;

        principal = new VouchrPrincipal();
        try {
            principal.setSubject(claims.getSubject());
            principal.setAudience(claims.getAudience().get(0));
            principal.setIpAddress(claims.getStringClaim("ip"));
            principal.setVoucherId(claims.getStringClaim("vouchrId"));
        } catch (ParseException ex) {
            logger.log(Level.WARNING, "Error parsing claims", ex);
        }


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;

    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }


    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return principal.getName();
    }


}
