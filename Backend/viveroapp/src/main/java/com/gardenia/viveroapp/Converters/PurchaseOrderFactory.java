package com.gardenia.viveroapp.Converters;

import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.PurchaseOrderDTO;
import com.gardenia.viveroapp.Model.PurchaseOrder;

@Component
public class PurchaseOrderFactory implements AbstractFactory<PurchaseOrder, PurchaseOrderDTO> {

    @Override
    public PurchaseOrderDTO toDTO(PurchaseOrder entity) {
        if (entity == null) {
            return null;
        } else {
            PurchaseOrderDTO dto = PurchaseOrderDTO.builder()
                    .idorder(entity.getIdorder())
                    .date(entity.getDate())
                    .amount(entity.getAmount())
                    .state(entity.getState())
                    .personId(entity.getPerson().getId())
                    .build();
            return dto;
        }
    }

    @Override
    public PurchaseOrder DTOToEntity(PurchaseOrderDTO dto) {
        if (dto == null) {
            return null;
        } else {
            PurchaseOrder entity = new PurchaseOrder(dto.getIdorder(), dto.getDate(), dto.getAmount(), dto.getState(),
                    null);
            return entity;
        }
    }

}
