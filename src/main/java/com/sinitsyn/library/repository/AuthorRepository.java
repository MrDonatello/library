package com.sinitsyn.library.repository;

import com.sinitsyn.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Query("select a from Author a where a.firstName= :firstName and  a.lastName = :lastName and a.yearOfBirth = :yearOfBirth")
    Author getAuthorId(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("yearOfBirth") int yearOfBirth);
}
