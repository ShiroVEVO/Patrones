package com.gardenia.viveroapp.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct", columnDefinition = "INT UNSIGNED")
    private Integer idproduct;

    @Column(name = "name", columnDefinition = "VARCHAR(80)", nullable = false)
    private String name;

    @Column(name = "associated_cost", columnDefinition = "MEDIUMINT(1) UNSIGNED", nullable = false)
    private Integer associatedCost;

    @Column(name = "stock", columnDefinition = "SMALLINT(1) ZEROFILL UNSIGNED", nullable = false)
    private Integer stock;

    @Column(name = "description", columnDefinition = "VARCHAR(400)", nullable = false)
    private String description;

    // private BaseCost basecost;

    @ManyToOne
    @JoinColumn(name = "product_idproduct")
    private Product parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> children;
}
