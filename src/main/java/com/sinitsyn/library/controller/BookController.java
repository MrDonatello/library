package com.sinitsyn.library.controller;


import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Book;
import com.sinitsyn.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> books() {
        return bookService.findAll();
    }

    @GetMapping("{id}")
    public Book getOneBook(@PathVariable("id") Book book) throws ServiceException {
        return bookService.findBookById(book);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) throws ServiceException {
        return bookService.addBook(book);
    }

    @PutMapping("{id}")
    public Book updateBook(@PathVariable("id") Book bookFromDataBase, @RequestBody Book updatedBook) {
        return bookService.updateBook(bookFromDataBase, updatedBook);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Book book) {
        bookService.deleteBook(book);
    }
}
