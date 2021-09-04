package com.sinitsyn.library.controller;

import com.sinitsyn.library.dto.request.AuthorDto;
import com.sinitsyn.library.dto.response.AuthorDtoResponse;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("library/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDtoResponse> authors() {
        return authorService.findAll();
    }

    @GetMapping("{id}")
    public AuthorDtoResponse getOneAuthor(@PathVariable Long id) throws ServiceException {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    public AuthorDtoResponse addAuthor(@RequestBody @Valid AuthorDto authorDto) throws ServiceException {
        return authorService.addAuthor(authorDto);
    }

    @PutMapping("{id}")
    public AuthorDtoResponse updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDto updatedAuthor) throws ServiceException {
        return authorService.updateAuthor(updatedAuthor, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
