package com.sinitsyn.library.dto.request;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.MaxLengthInterface;
import com.sinitsyn.library.validation.MinLengthInterface;
import com.sinitsyn.library.validation.PatternInterface;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthorDto {


    private Long id;

    @MaxLengthInterface
    @PatternInterface(regexp = "^[A-Za-z0-9А-Яа-я]+$", message = "first name contains invalid characters", error = ErrorCode.INVALID_FIRST_NAME)
    private String firstName;

    @MaxLengthInterface
    @PatternInterface(regexp = "^[A-Za-z0-9А-Яа-я]+$", message = "last name contains invalid characters", error = ErrorCode.INVALID_LAST_NAME)
    private String lastName;

    @MaxLengthInterface
    private String patronymic;

    private String biography;

    @MinLengthInterface(error = ErrorCode.INCORRECT_YEAR_OF_BIRTH, message = "incorrect year of birth")
    private int yearOfBirth;
}
