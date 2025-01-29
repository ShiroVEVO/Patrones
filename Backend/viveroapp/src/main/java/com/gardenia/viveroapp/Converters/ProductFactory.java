package com.gardenia.viveroapp.Converters;

import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.ProductDTO;
import com.gardenia.viveroapp.Model.Product;

@Component
public class ProductFactory implements AbstractFactory<Product, ProductDTO> {

    @Override
    public ProductDTO toDTO(Product entity) {
        if (entity == null) {
            return null;
        } else {
            ProductDTO dto = ProductDTO.builder()
                    .idproduct(entity.getIdproduct())
                    .name(entity.getName())
                    .associatedCost(entity.getAssociatedCost())
                    .stock(entity.getStock())
                    .description(entity.getDescription()).build();
            return dto;
        }
    }

    @Override
    public Product DTOToEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Product entity = new Product();
            entity.setIdproduct(dto.getIdproduct());
            entity.setName(dto.getName());
            entity.setAssociatedCost(dto.getAssociatedCost());
            entity.setStock(dto.getStock());
            entity.setDescription(dto.getDescription());
            // FUERA SE PONEN EL PARENT Y LOS CHILDREN
            return entity;
        }
    }

}
