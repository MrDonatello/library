package com.sinitsyn.library.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Person {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String patronymic;
    @Column(name = "year_of_birth")
    private int yearOfBirth;

}
