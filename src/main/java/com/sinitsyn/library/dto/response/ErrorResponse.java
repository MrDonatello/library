package com.sinitsyn.library.dto.response;

import com.sinitsyn.library.exceptions.ApiError;
import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private List<ApiError> errors;

    public ErrorResponse(List<ApiError> errors) {
        this.errors = errors;
    }

}
