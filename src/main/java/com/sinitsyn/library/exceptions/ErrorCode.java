package com.sinitsyn.library.exceptions;

public enum ErrorCode {

    INVALID_FIRST_NAME("first name field invalid"),
    INVALID_PATRONYMIC("patronymic field invalid"),
    INVALID_LAST_NAME("last name field is invalid"),
    INVALID_MAX_LENGTH(""),
    INVALID_MIN_LENGTH(""),
    ERROR_ADD_TO_DATABASE("error add object to database"),
    ERROR_DELETE_OBJECT_FROM_DATABASE("error delete object from database"),
    ERROR_FIND_OBJECT_TO_DATABASE("error find object to database"),
    DATABASE_ACCESS_ERROR("database access error");

    private String error;

    ErrorCode(String errorString) {

        this.error = errorString;
    }

    public String getErrorString() {

        return error;
    }
}
