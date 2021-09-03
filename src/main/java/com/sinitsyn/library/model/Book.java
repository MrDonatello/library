package com.sinitsyn.library.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column
    private String title;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;
    @Column(name = "genre")
    private String genre;
    @Column
    private String isbn;
}
