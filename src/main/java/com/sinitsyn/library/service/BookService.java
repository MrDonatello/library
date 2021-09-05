package com.sinitsyn.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.BookAuthorDto;
import com.sinitsyn.library.dto.request.BookDto;
import com.sinitsyn.library.dto.response.BookDtoResponse;
import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Book;
import com.sinitsyn.library.model.BookAuthor;
import com.sinitsyn.library.repository.BookAuthorRepository;
import com.sinitsyn.library.repository.BookRepository;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final ObjectMapper objectMapper;
    private final AuthorService authorService;


    public BookService(BookRepository bookRepository, BookAuthorRepository bookAuthorRepository, ObjectMapper objectMapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    public List<BookDtoResponse> findAll() {
        return (objectMapper.convertValue(bookRepository.findAll(), new TypeReference<List<BookDtoResponse>>() {
        }));
    }

    public BookDtoResponse findBookById(Long id) throws ServiceException {
        Book user = bookRepository.findById(id).orElseThrow(() -> new ServiceException(new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findBookById Method", "book with id = " + id + " not found")));
        return objectMapper.convertValue(user, BookDtoResponse.class);
    }

    public BookDtoResponse addBook(BookDto bookDto) throws ServiceException {
        Book book = objectMapper.convertValue(bookDto, Book.class);
        Book saveBook;
        try {
            saveBook = bookRepository.save(book);
        } catch (RuntimeException e) {
            throw new ServiceException(new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addBookMethod", NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        }
        return objectMapper.convertValue(saveBook, BookDtoResponse.class);
    }

    public BookDtoResponse updateBook(BookDto updatedBook, Long id) throws ServiceException {
        updatedBook.setId(id);
        findBookById(id);
        return addBook(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteBookAuthor(Long id, BookAuthorDto bookAuthorDto) {
        bookAuthorRepository.deleteBooks(authorService.getAuthorId(bookAuthorDto), id);
    }

    public void addBookAuthor(Long id, BookAuthorDto bookAuthorDto) throws ServiceException {
        bookAuthorDto.setAuthorId(authorService.getAuthorId(bookAuthorDto));
        bookAuthorDto.setBookId(id);
        try {
            bookAuthorRepository.save(objectMapper.convertValue(bookAuthorDto, BookAuthor.class));
        } catch (RuntimeException e) {
            throw new ServiceException(new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addBookAuthor Method", NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        }
    }
}
