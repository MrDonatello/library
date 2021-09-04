package com.sinitsyn.library.dto.request;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.MaxLengthInterface;
import com.sinitsyn.library.validation.MaxLengthIsbnInterface;
import com.sinitsyn.library.validation.MinLengthInterface;
import lombok.Data;

@Data
public class BookDto {

    private Long id;

    @MaxLengthInterface
    private String title;

    @MinLengthInterface(error = ErrorCode.INCORRECT_YEAR_OF_PUBLISHING, message = "incorrect year of publishing")
    private int yearOfPublishing;

    @MaxLengthInterface
    private String genre;

    @MaxLengthIsbnInterface
    private String isbn;
}
