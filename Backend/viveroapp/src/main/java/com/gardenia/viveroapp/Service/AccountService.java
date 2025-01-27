package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Converters.AccountFactory;
import com.gardenia.viveroapp.DTO.AccountDTO;
import com.gardenia.viveroapp.DTO.AccountPostDTO;
import com.gardenia.viveroapp.Model.Account;
import com.gardenia.viveroapp.Model.Person;
import com.gardenia.viveroapp.Model.Role;
import com.gardenia.viveroapp.Repository.AccountRepository;
import com.gardenia.viveroapp.Repository.PersonRepository;
import com.gardenia.viveroapp.Repository.RoleRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountFactory accountFactory;

    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> accountsDTO = new ArrayList<AccountDTO>();
        List<Account> accounts = accountRepository.findAll();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            AccountDTO accountDTO = accountFactory.toDTO(account);
            accountsDTO.add(accountDTO);
        }
        return accountsDTO;
    }

    public AccountDTO getAccountById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            AccountDTO accountDTO = accountFactory.toDTO(account);
            return accountDTO;
        } else {
            return null;
        }
    }

    public AccountDTO addAccount(AccountPostDTO accountPostDTO) {
        Optional<Person> optionalPerson = personRepository.findById(accountPostDTO.getAccountDTO().getPersonId());
        Optional<Role> optionalRole = roleRepository.findById(accountPostDTO.getAccountDTO().getRol().getId());
        if (optionalPerson.isPresent() && optionalRole.isPresent()) {
            Account account = accountFactory.postDTOToEntity(accountPostDTO);
            Person person = optionalPerson.get();
            Role role = optionalRole.get();
            account.setPerson(person);
            account.setRole(role);
            accountRepository.save(account);
            return accountPostDTO.getAccountDTO();
        } else {
            return null;
        }

    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    public AccountDTO updateAccount(AccountPostDTO accountPostDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountPostDTO.getAccountDTO().getIdaccount());
        if (optionalAccount.isPresent()) {
            Account savedAccount = optionalAccount.get();
            savedAccount.setPassword(accountPostDTO.getPassword());
            Optional<Role> optionalRole = roleRepository.findById(accountPostDTO.getAccountDTO().getRol().getId());
            if (optionalRole.isPresent()) {
                savedAccount.setRole(optionalRole.get());
            }
            savedAccount.setEmail(accountPostDTO.getAccountDTO().getEmail());
            savedAccount.setBirthdate(accountPostDTO.getAccountDTO().getBirthdate());
            accountRepository.save(savedAccount);
            return accountPostDTO.getAccountDTO();
        } else {
            return null;
        }
    }
}
