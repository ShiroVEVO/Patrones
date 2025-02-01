package com.gardenia.viveroapp.DTO;

import lombok.Data;

@Data
public class RoleDTO {

    private Integer id;

    private String name;

    public RoleDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
