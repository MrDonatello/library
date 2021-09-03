package com.sinitsyn.library.exceptions;

import java.util.List;

public class ServiceException extends Exception {

    private String errorCode;

    private List<ApiError> errors;

    public ServiceException(List<ApiError> errors) {
        this.errors = errors;
    }


    List<ApiError> getErrors() {
        return errors;
    }
}
