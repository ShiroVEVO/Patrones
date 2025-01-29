package com.gardenia.viveroapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.AccountDTO;
import com.gardenia.viveroapp.DTO.LoginDTO;
import com.gardenia.viveroapp.Service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<AccountDTO> login(@RequestBody LoginDTO loginDTO) {
        AccountDTO accountDTO = loginService.login(loginDTO);
        if (accountDTO != null) {
            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountDTO>(HttpStatus.UNAUTHORIZED);
        }
    }
}
