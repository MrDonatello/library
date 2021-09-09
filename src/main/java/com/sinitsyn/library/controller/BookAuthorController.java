package com.sinitsyn.library.controller;

import com.sinitsyn.library.dto.request.BookAuthorDto;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("library/bookAuthor")
public class BookAuthorController {

    private final BookService bookService;

    public BookAuthorController(BookService bookService) {
        this.bookService = bookService;
    }


    @PutMapping("remove/{id}")
    public void deleteBookAuthor(@PathVariable Long id, @RequestBody @Valid BookAuthorDto bookAuthorDto) {
        bookService.deleteBookAuthor(id, bookAuthorDto);
    }

    @PutMapping("add/{id}")
    public void addBookAuthor(@PathVariable Long id, @RequestBody @Valid BookAuthorDto bookAuthorDto) throws ServiceException {
        bookService.addBookAuthor(id, bookAuthorDto);
    }
}
