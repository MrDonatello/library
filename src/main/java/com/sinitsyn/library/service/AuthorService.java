package com.sinitsyn.library.service;

import com.sinitsyn.library.model.Author;
import com.sinitsyn.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
    authorRepository.save(author);
    }

    public void updateAuthor(Author author) {

    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }


}
