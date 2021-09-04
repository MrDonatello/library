package com.sinitsyn.library.dto.request;

import com.sinitsyn.library.validation.MaxLengthInterface;
import lombok.Data;

@Data
public class GenreDto {

    private Long id;

    @MaxLengthInterface
    private String title;

    private String description;
}
