package com.gardenia.viveroapp.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private Integer idproduct;
    private String name;
    private Integer associatedCost;
    private Integer stock;
    private String description;
    private BaseCostDTO baseCost;
    private List<ProductDTO> variants;
    private List<ImageDTO> images;

}
