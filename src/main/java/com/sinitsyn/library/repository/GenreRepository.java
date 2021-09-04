package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

   // Genre findByTitle(String title);
}
