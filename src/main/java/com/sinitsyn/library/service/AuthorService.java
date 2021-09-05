package com.sinitsyn.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.AuthorDto;
import com.sinitsyn.library.dto.request.BookAuthorDto;
import com.sinitsyn.library.dto.response.AuthorDtoResponse;
import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Author;
import com.sinitsyn.library.repository.AuthorRepository;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ObjectMapper objectMapper;


    public AuthorService(AuthorRepository authorRepository, ObjectMapper objectMapper) {
        this.authorRepository = authorRepository;
        this.objectMapper = objectMapper;
    }

    public List<AuthorDtoResponse> findAll() {
        return (objectMapper.convertValue(authorRepository.findAll(), new TypeReference<List<AuthorDtoResponse>>() {
        }));
    }

    public AuthorDtoResponse findAuthorById(Long id) throws ServiceException {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ServiceException(new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findAuthorById Method", "author with id = " + id + " not found")));
        return objectMapper.convertValue(author, AuthorDtoResponse.class);

    }

    public AuthorDtoResponse addAuthor(AuthorDto authorDto) throws ServiceException {
        Author author = objectMapper.convertValue(authorDto, Author.class);
        Author saveAuthor;
        try {
            saveAuthor = authorRepository.save(author);
        } catch (RuntimeException e) {
            throw new ServiceException(new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addAuthorMethod", NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        }
        return objectMapper.convertValue(saveAuthor, AuthorDtoResponse.class);
    }

    public AuthorDtoResponse updateAuthor(AuthorDto updatedAuthor, Long id) throws ServiceException {
        updatedAuthor.setId(id);
        findAuthorById(id);
        return addAuthor(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    Long getAuthorId(BookAuthorDto bookAuthorDto) {
        return authorRepository.getAuthorId(bookAuthorDto.getFirstName(), bookAuthorDto.getLastName(), bookAuthorDto.getYearOfBirth()).getId();
    }
}
