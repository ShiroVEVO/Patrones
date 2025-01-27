package com.gardenia.viveroapp.Converters;

public interface AbstractFactory<Entities, DTOs> {
    DTOs toDTO(Entities entity);

    Entities DTOToEntity(DTOs dto);
}
