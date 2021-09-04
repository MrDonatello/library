package com.sinitsyn.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.GenreDto;
import com.sinitsyn.library.dto.response.GenreDtoResponse;
import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Genre;
import com.sinitsyn.library.repository.GenreRepository;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final ObjectMapper objectMapper;

    public GenreService(GenreRepository genreRepository, ObjectMapper objectMapper) {
        this.genreRepository = genreRepository;
        this.objectMapper = objectMapper;
    }

    public List<GenreDtoResponse> findAll() {
        return (objectMapper.convertValue(genreRepository.findAll(), new TypeReference<List<GenreDtoResponse>>() {
        }));
    }

    public GenreDtoResponse findGenreById(Long id) throws ServiceException {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ServiceException(new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findGenreById Method", "genre with id = " + id + " not found")));
        return objectMapper.convertValue(genre, GenreDtoResponse.class);
    }

    public GenreDtoResponse addGenre(GenreDto genreDto) throws ServiceException {
        Genre genre = objectMapper.convertValue(genreDto, Genre.class);
        Genre saveGenre;
        try {
            saveGenre = genreRepository.save(genre);
        } catch (RuntimeException e) {
            throw new ServiceException(new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "addGenreMethod", NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        }
        return objectMapper.convertValue(saveGenre, GenreDtoResponse.class);
    }

    public GenreDtoResponse updateGenre(GenreDto updatedGenre, Long id) throws ServiceException {
        updatedGenre.setId(id);
        findGenreById(id);
        return addGenre(updatedGenre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
