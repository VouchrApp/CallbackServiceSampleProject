package ca.vouchr.payments.callback.config;

import java.security.Principal;

public class VouchrPrincipal implements Principal {
    private String ipAddress;
    private String subject;
    private String voucherId;
    private String audience;

    @Override
    public String getName() {
        return subject;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
