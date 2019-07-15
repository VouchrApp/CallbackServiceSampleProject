package ca.vouchr.payments.callback.dto;

/**
 * Created by aaron on 2016-06-17.
 */
public class ErrorFieldDto {
    private String field;
    private String code;
    private String message;

    public ErrorFieldDto() {

    }

    public ErrorFieldDto(String field, String code, String details) {
        this.field = field;
        this.code = code;
        this.message = details;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String errorEnum) {
        this.code = errorEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
