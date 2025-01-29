package com.gardenia.viveroapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Converters.AccountFactory;
import com.gardenia.viveroapp.DTO.AccountDTO;
import com.gardenia.viveroapp.DTO.LoginDTO;
import com.gardenia.viveroapp.Model.Account;
import com.gardenia.viveroapp.Repository.AccountRepository;

@Service
public class LoginService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountFactory accountFactory;

    public AccountDTO login(LoginDTO loginDTO) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(loginDTO.getEmail());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getPassword().equals(loginDTO.getPassword())) {
                return accountFactory.toDTO(account);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
