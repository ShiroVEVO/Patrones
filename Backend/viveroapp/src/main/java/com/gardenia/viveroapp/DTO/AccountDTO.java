package com.gardenia.viveroapp.DTO;

import java.sql.Date;

import com.gardenia.viveroapp.Model.PersonId;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private Integer idaccount;
    private String email;
    private Date birthdate;
    private RoleDTO rol;
    private PersonId personId;
}
