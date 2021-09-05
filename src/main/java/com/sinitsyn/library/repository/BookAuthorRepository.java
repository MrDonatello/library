package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {


    @Modifying
    @Transactional
    @Query("delete from BookAuthor b where b.authorId = :authorId and  b.bookId = :bookId")
    void deleteBooks(@Param("authorId") Long authorId, @Param("bookId") Long bookId);
}
