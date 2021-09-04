package com.sinitsyn.library.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GenreDtoResponse {

    private Long id;

    private String title;

    private String description;
}
