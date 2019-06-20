package ca.vouchr.payments.callback.dto;

import java.util.HashMap;
import java.util.Map;

public class PaymentRedeemDto {

    private String type;

    private Map<String, String> properties = new HashMap<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
