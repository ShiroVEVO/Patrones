package com.gardenia.viveroapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gardenia.viveroapp.Model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
