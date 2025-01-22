package com.gardenia.viveroapp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
    @EmbeddedId
    private PersonId id;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

    @Column(name = "lastname", columnDefinition = "VARCHAR(40)", nullable = false)
    private String lastname;

    @Column(name = "phone_number", columnDefinition = "INT UNSIGNED", nullable = false)
    private Long phone_number;
}
