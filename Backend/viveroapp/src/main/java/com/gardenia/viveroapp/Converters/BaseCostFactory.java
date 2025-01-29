package com.gardenia.viveroapp.Converters;

import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.BaseCostDTO;
import com.gardenia.viveroapp.Model.BaseCost;

@Component
public class BaseCostFactory implements AbstractFactory<BaseCost, BaseCostDTO> {

    @Override
    public BaseCostDTO toDTO(BaseCost entity) {
        if (entity != null) {
            return BaseCostDTO.builder()
                    .idbase_cost(entity.getIdbase_cost())
                    .name(entity.getName())
                    .amount(entity.getAmount())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public BaseCost DTOToEntity(BaseCostDTO dto) {
        if (dto != null) {
            BaseCost entity = new BaseCost();
            entity.setIdbase_cost(dto.getIdbase_cost());
            entity.setName(dto.getName());
            entity.setAmount(dto.getAmount());
            return entity;
        } else {
            return null;
        }
    }

}
