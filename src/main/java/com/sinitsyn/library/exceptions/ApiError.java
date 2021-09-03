package com.sinitsyn.library.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApiError {
    private String errorCode;
    private String field;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public ApiError(String errorCode, String field, String message) {
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public ApiError() {
    }


}
