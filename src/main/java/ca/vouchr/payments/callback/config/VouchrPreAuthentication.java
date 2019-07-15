package ca.vouchr.payments.callback.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class VouchrPreAuthentication implements Authentication {
    private static final Logger logger = Logger.getLogger(VouchrPreAuthentication.class.getName());

    private String jwtToken;
    private byte[] bodySignature;
    private byte[] bodyContent;


    public VouchrPreAuthentication() {

    }

    public VouchrPreAuthentication(String bearerToken, byte[] bodyContent, byte[] bodySignature) {
        this.jwtToken = bearerToken;
        this.bodySignature = bodySignature;
        this.bodyContent = bodyContent;
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
        return null;

    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }


    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        ;
    }

    @Override
    public String getName() {
        return null;
    }

    public byte[] getBodySignature() {
        return bodySignature;
    }

    public byte[] getBodyContent() {
        return bodyContent;
    }

}
