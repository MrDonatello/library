package com.sinitsyn.library.controller;

import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Genre;
import com.sinitsyn.library.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> genres() {
        return genreService.findAll();
    }

    @GetMapping("{id}")
    public Genre getOneGenre(@PathVariable("id") Genre genre) throws ServiceException {
        return genreService.findGenreById(genre);
    }

    @PostMapping
    public Genre addGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @PutMapping("{id}")
    public Genre updateGenre(@PathVariable("id") Genre genreFromDataBase, @RequestBody Genre updatedGenre) {
        return genreService.updateGenre(genreFromDataBase, updatedGenre);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Genre genre) {
        genreService.deleteGenre(genre);
    }
}
