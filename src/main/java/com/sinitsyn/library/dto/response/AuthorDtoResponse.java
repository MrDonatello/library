package com.sinitsyn.library.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthorDtoResponse {


    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int yearOfBirth;
    private String biography;

}
