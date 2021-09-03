package com.sinitsyn.library.dto.request;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.MaxNameLength;
import com.sinitsyn.library.validation.PatternInterface;
import lombok.Data;

@Data
public class AuthorDto {


    private Long id;

    @MaxNameLength
    @PatternInterface(regexp = "^[A-Za-z0-9А-Яа-я]+$", message = "first name contains invalid characters", error = ErrorCode.INVALID_FIRST_NAME)
    private String firstName;

    @MaxNameLength
    @PatternInterface(regexp = "^[A-Za-z0-9А-Яа-я]+$", message = "last name contains invalid characters", error = ErrorCode.INVALID_LAST_NAME)
    private String lastName;

    @MaxNameLength
    private String patronymic;

    private String biography;

    private int yearOfBirth;
}
