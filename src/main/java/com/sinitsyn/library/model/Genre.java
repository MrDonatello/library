package com.sinitsyn.library.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column
    private String title;
    @Column
    private String description;

}
