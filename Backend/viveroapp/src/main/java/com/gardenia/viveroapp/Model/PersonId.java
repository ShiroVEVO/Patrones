package com.gardenia.viveroapp.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PersonId implements Serializable {
    private String doc_type;
    private Integer doc_number;
}