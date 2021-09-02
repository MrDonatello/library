package com.sinitsyn.library.service;

import com.sinitsyn.library.exceptions.NotFoundException;
import com.sinitsyn.library.model.Author;
import com.sinitsyn.library.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) {

        if (validate(author)) {
            return authorRepository.save(author);
        } else return null;
    }

    public Author updateAuthor(Author authorFromDataBase, Author updatedAuthor) {
        BeanUtils.copyProperties(updatedAuthor, authorFromDataBase, "id");
        return authorRepository.save(authorFromDataBase);
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public Author findAuthorById(Author author) {
        return authorRepository.findById(author.getId()).orElseThrow(NotFoundException::new);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    private boolean validate(Author author) {

        return author.getFirstName() != null && author.getLastName() != null;
    }

}
