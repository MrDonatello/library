package com.sinitsyn.library.exceptions;


import com.sinitsyn.library.dto.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ApiError> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            for (Object o : Objects.requireNonNull(fieldError.getArguments())) {
                if (o.getClass().equals(ErrorCode.class)) {
                    ErrorCode errorCode = (ErrorCode) o;
                    errors.add(new ApiError(errorCode.name(), fieldError.getField(), fieldError.getDefaultMessage()));
                }
            }
        }
        return new ErrorResponse(errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ErrorResponse SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        String[] field = ex.getMessage().split("'", 4);
        List<ApiError> apiErrors = new ArrayList<>();
        ApiError apiError = new ApiError(ErrorCode.DATABASE_ACCESS_ERROR.name(), field[3], ex.getMessage());
        apiErrors.add(apiError);
        return new ErrorResponse(apiErrors);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse Ex(ServiceException ex) {
        return new ErrorResponse(ex.getErrors());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ErrorResponse Exception(ServiceException ex) {

        return new ErrorResponse(ex.getErrors());
    }
}
