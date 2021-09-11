package com.sinitsyn.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoResponse {


    private Long id;

    private String title;

    private int yearOfPublishing;

    private String genre;

    private String isbn;
}
