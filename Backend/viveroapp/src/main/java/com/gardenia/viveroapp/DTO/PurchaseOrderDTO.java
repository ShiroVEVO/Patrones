package com.gardenia.viveroapp.DTO;

import java.time.LocalDateTime;

import com.gardenia.viveroapp.Model.PersonId;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderDTO {
    private Integer idorder;
    private LocalDateTime date;
    private Integer amount;
    private String state;
    private PersonId personId;
}
