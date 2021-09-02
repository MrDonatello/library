package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
