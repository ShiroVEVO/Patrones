package com.gardenia.viveroapp.Converters;

import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.RoleDTO;
import com.gardenia.viveroapp.Model.Role;

@Component
public class RoleFactory implements AbstractFactory<Role, RoleDTO> {

    @Override
    public RoleDTO toDTO(Role entity) {
        if (entity == null) {
            return null;
        } else {
            RoleDTO dto = new RoleDTO(entity.getIdrole(), entity.getName());
            return dto;
        }
    }

    @Override
    public Role DTOToEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Role entity = new Role();
            entity.setName(dto.getName());
            entity.setIdrole(dto.getId());
            return entity;
        }
    }
}
