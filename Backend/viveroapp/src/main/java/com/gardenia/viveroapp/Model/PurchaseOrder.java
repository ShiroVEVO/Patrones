package com.gardenia.viveroapp.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpurchase_order", columnDefinition = "INT UNSIGNED")
    private Integer idorder;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount", columnDefinition = "MEDIUMINT(1)", nullable = false)
    private Integer amount;

    @Column(name = "state", columnDefinition = "VARCHAR(40)", nullable = false)
    private String state;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "person_doc_number", referencedColumnName = "doc_number"),
            @JoinColumn(name = "person_doc_type", referencedColumnName = "doc_type")

    })
    private Person person;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

}
