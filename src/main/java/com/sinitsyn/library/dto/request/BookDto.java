package com.sinitsyn.library.dto.request;

import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.validation.MaxLengthInterface;
import com.sinitsyn.library.validation.MaxLengthIsbnInterface;
import com.sinitsyn.library.validation.MinLengthInterface;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private Long id;

    @NonNull
    @MaxLengthInterface
    private String title;

    @NonNull
    @MinLengthInterface(error = ErrorCode.INCORRECT_YEAR_OF_PUBLISHING, message = "incorrect year of publishing")
    private int yearOfPublishing;

    @MaxLengthInterface
    private String genre;

    @NonNull
    @MaxLengthIsbnInterface
    private String isbn;
}
