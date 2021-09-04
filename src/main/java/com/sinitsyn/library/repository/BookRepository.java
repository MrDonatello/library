package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, Long> {
    //@Query(value = "SELECT title, year_of_publishing, genre, isbn, first_name, last_name,patronymic,biography,year_of_birth from books LEFT JOIN books_authors ON (books.book_id = books_authors.book_id) LEFT JOIN authors a on books_authors.author_id = a.author_id")
}
