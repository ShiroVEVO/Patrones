package com.gardenia.viveroapp.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class BaseCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbase_cost", columnDefinition = "INT UNSIGNED")
    private Integer idbase_cost;

    @Column(name = "name", columnDefinition = "VARCHAR(80)")
    private String name;

    @Column(name = "amount", columnDefinition = "MEDIUMINT(1) UNSIGNED")
    private Integer amount;

    @OneToMany(mappedBy = "basecost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
