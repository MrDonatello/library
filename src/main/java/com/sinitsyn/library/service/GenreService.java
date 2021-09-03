package com.sinitsyn.library.service;

import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Genre;
import com.sinitsyn.library.repository.GenreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre addGenre(Genre genre) {
        if (validate(genre)) {
            return genreRepository.save(genre);
        } else return null;
    }

    public Genre updateGenre(Genre genreFromDataBase, Genre updatedGenre) {
        BeanUtils.copyProperties(updatedGenre, genreFromDataBase, "id");
        return genreRepository.save(genreFromDataBase);
    }

    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }

    public Genre findGenreById(Genre genre) throws ServiceException {
        try {
            return genreRepository.findById(genre.getId()).orElseThrow(RuntimeException::new);
        }catch (RuntimeException e){
            ArrayList<ApiError> errors = new ArrayList<>();
            ApiError apiError = new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findGenreByIdMethod", e.getCause().getMessage());
            errors.add(apiError);
            throw new ServiceException(errors);
        }
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    Boolean isContainsTitle(String genreTitle) {
        return genreRepository.findByTitle(genreTitle) != null;
    }


    private boolean validate(Genre genre) {

        return genre.getTitle() != null;
    }
}
