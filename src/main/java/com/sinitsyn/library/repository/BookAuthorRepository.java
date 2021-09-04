package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    //void deleteBookAuthorByAuthorIdAndBookId(Long book_id, Long author_id);
    void deleteByAuthorIdAndBookId(Long author_id, Long book_id);
}
