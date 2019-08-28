package ca.vouchr.payments.callback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private ErrorDetailsDto error;

    public ErrorDetailsDto getError() {
        return error;
    }

    public void setError(ErrorDetailsDto error) {
        this.error = error;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDetailsDto {

        private ErrorType type;
        private String code;
        private String message;

        private Collection<ErrorFieldDto> errorFields = new ArrayList<>();

        public ErrorType getType() {
            return type;
        }

        public void setType(ErrorType type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Collection<ErrorFieldDto> getErrorFields() {
            return errorFields;
        }

        public void setErrorFields(Collection<ErrorFieldDto> errorFields) {
            this.errorFields = errorFields;
        }


        public enum ErrorType {CREATOR_ERROR, RECIPIENT_ERROR, SERVER_ERROR, TEMPORARY_ERROR}
    }
}
