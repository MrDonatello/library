package com.sinitsyn.library.dto.request;

        import com.sun.istack.NotNull;
        import lombok.Data;

@Data
public class AuthorDto {

    @NotNull
    private Long author_id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String biography;

    private int yearOfBirth;



}
