package com.gardenia.viveroapp.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.AccountDTO;
import com.gardenia.viveroapp.DTO.AccountPostDTO;
import com.gardenia.viveroapp.Model.Account;

@Component
public class AccountFactory implements AbstractFactory<Account, AccountDTO> {

    @Autowired
    private RoleFactory roleFactory;

    @Override
    public AccountDTO toDTO(Account entity) {
        if (entity == null) {
            return null;
        } else {
            AccountDTO dto = AccountDTO.builder()
                    .idaccount(entity.getIdaccount())
                    .email(entity.getEmail())
                    .birthdate(entity.getBirthdate())
                    .rol(roleFactory.toDTO(entity.getRole()))
                    .personId(entity.getPerson().getId())
                    .build();
            return dto;
        }

    }

    @Override
    public Account DTOToEntity(AccountDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Account entity = new Account();
            entity.setIdaccount(dto.getIdaccount());
            entity.setEmail(dto.getEmail());
            entity.setBirthdate(dto.getBirthdate());
            return entity;
        }
    }

    public Account postDTOToEntity(AccountPostDTO postDTO) {
        if (postDTO == null) {
            return null;
        } else {
            Account entity = DTOToEntity(postDTO.getAccountDTO());
            entity.setPassword(postDTO.getPassword());
            return entity;
        }
    }

    public AccountPostDTO toPostDTO(Account entity) {
        if (entity == null) {
            return null;
        } else {
            AccountPostDTO postDTO = new AccountPostDTO();
            AccountDTO dto = AccountDTO.builder()
                    .idaccount(entity.getIdaccount())
                    .email(entity.getEmail())
                    .birthdate(entity.getBirthdate())
                    .personId(entity.getPerson().getId())
                    .rol(roleFactory.toDTO(entity.getRole())).build();
            postDTO.setAccountDTO(dto);
            postDTO.setPassword(entity.getPassword());
            return postDTO;
        }

    }

}
