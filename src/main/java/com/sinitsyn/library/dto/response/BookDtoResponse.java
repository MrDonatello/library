package com.sinitsyn.library.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookDtoResponse {

    private Long id;

    private String title;

    private int yearOfPublishing;

    private String genre;

    private String isbn;
}
