package com.sinitsyn.library.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;
    @Column(name = "genre_id")
    private int genre;
    @Column
    private String isbn;
}
