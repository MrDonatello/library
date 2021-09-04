package com.sinitsyn.library.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books_authors")
public class BookAuthor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "author_id")
    private Long authorId;
}
