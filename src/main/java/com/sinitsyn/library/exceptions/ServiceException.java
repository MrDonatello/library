package com.sinitsyn.library.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ServiceException extends Exception {

    private String errorCode;

    private List<ApiError> errors;

    public ServiceException(List<ApiError> errors) {
        this.errors = errors;
    }

    public ServiceException(ApiError errors) {
        List<ApiError> list = new ArrayList<>();
        list.add(errors);
        this.errors = list;
    }


    List<ApiError> getErrors() {
        return errors;
    }
}
