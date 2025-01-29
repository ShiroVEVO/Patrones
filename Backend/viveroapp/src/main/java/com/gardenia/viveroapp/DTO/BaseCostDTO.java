package com.gardenia.viveroapp.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseCostDTO {
    private Integer idbase_cost;
    private String name;
    private Integer amount;
}
