package com.sinitsyn.library.controller;

import com.sinitsyn.library.dto.request.GenreDto;
import com.sinitsyn.library.dto.response.GenreDtoResponse;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Genre;
import com.sinitsyn.library.service.GenreService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("library/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDtoResponse> genres() {
        return genreService.findAll();
    }

    @GetMapping("{id}")
    public GenreDtoResponse getOneGenre(@PathVariable Long id) throws ServiceException {
        return genreService.findGenreById(id);
    }

    @PostMapping
    public GenreDtoResponse addGenre(@RequestBody @Valid GenreDto genre)throws ServiceException {
        return genreService.addGenre(genre);
    }

    @PutMapping("{id}")
    public GenreDtoResponse updateGenre(@PathVariable Long id, @RequestBody @Valid GenreDto updatedGenre)throws ServiceException {
        return genreService.updateGenre(updatedGenre, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
