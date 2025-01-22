package com.gardenia.viveroapp.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount", columnDefinition = "INT UNSIGNED")
    private Integer idaccount;

    @Column(name = "email", columnDefinition = "VARCHAR(40)", nullable = false)
    private String email;

    // Revisar en el futuro que longitud tendrá la contraseña encriptada.
    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;

    @Column(name = "birthdate", columnDefinition = "DATE", nullable = false)
    private Date birthdate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "person_doc_number", referencedColumnName = "doc_number"),
            @JoinColumn(name = "person_doc_type", referencedColumnName = "doc_type"),
    })
    private Person person;

    @ManyToOne
    @JoinColumn(name = "role_idrole")
    private Role role;

}
