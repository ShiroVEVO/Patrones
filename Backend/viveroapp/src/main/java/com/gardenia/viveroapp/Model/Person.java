package com.gardenia.viveroapp.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @EmbeddedId
    private PersonId id;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

    @Column(name = "lastname", columnDefinition = "VARCHAR(40)", nullable = false)
    private String lastname;

    @Column(name = "phone_number", columnDefinition = "INT UNSIGNED", nullable = false)
    private Long phone_number;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrder> purchase_orders = new ArrayList<>();
}
