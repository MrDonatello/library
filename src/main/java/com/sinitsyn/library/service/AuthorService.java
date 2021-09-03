package com.sinitsyn.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.AuthorDto;
import com.sinitsyn.library.dto.response.AuthorDtoResponse;
import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Author;
import com.sinitsyn.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDtoResponse authorDtoResponse;
    private final ObjectMapper objectMapper;


    public AuthorService(AuthorRepository authorRepository, AuthorDtoResponse authorDtoResponse, ObjectMapper objectMapper) {
        this.authorRepository = authorRepository;
        this.authorDtoResponse = authorDtoResponse;
        this.objectMapper = objectMapper;
    }

    public List<AuthorDtoResponse> findAll() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDtoResponse> authorDtoRes = new ArrayList<>();
        for (Author author : authorList) {
            authorDtoRes.add(objectMapper.convertValue(author, authorDtoResponse.getClass()));
        }
        return authorDtoRes;
    }

    public AuthorDtoResponse findAuthorById(Long id) throws ServiceException {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            ArrayList<ApiError> errors = new ArrayList<>();
            ApiError apiError = new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findAuthorByIdMethod", "error find author by id");
            errors.add(apiError);
            throw new ServiceException(errors);
        }
        return objectMapper.convertValue(author, AuthorDtoResponse.class);

    }

    public AuthorDtoResponse addAuthor(AuthorDto authorDto) throws ServiceException {

        Author author = objectMapper.convertValue(authorDto, Author.class);
        Author saveAuthor;
        try {
            saveAuthor = authorRepository.save(author);
        } catch (RuntimeException e) {
            ArrayList<ApiError> errors = new ArrayList<>();
            ApiError apiError = new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addAuthorMethod", e.getCause().getMessage());
            errors.add(apiError);
            throw new ServiceException(errors);
        }
        return objectMapper.convertValue(saveAuthor, AuthorDtoResponse.class);
    }

    public AuthorDtoResponse updateAuthor(AuthorDto updatedAuthor, Long id) throws ServiceException {
        updatedAuthor.setId(id);
        return addAuthor(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
