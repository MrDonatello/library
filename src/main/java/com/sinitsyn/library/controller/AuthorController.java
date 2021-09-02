package com.sinitsyn.library.controller;

import com.sinitsyn.library.model.Author;
import com.sinitsyn.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> authors() {
        return authorService.findAll();
    }

    @GetMapping("{id}")
    public Author getOneAuthor(@PathVariable("id") Author author) {
        return authorService.findAuthorById(author);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("{id}")
    public Author updateAuthor(@PathVariable("id") Author authorFromDataBase, @RequestBody Author updatedAuthor) {
        return authorService.updateAuthor(authorFromDataBase, updatedAuthor);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Author author) {
        authorService.deleteAuthor(author);
    }
}
