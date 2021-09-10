package com.sinitsyn.library.controller;


import com.sinitsyn.library.dto.request.BookDto;
import com.sinitsyn.library.dto.response.BookDtoResponse;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("library/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDtoResponse> books() {
        return bookService.findAll();
    }

    @GetMapping("{id}")
    public BookDtoResponse getOneBook(@PathVariable long id) throws ServiceException {
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookDtoResponse addBook(@RequestBody @Valid BookDto bookDto) throws ServiceException {
        return bookService.addBook(bookDto);
    }

    @PutMapping("{id}")
    public BookDtoResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookDto updatedBook) throws ServiceException {
        return bookService.updateBook(updatedBook, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


}
