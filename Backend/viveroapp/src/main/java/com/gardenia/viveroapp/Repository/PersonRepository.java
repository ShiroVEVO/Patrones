package com.gardenia.viveroapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gardenia.viveroapp.Model.Person;
import com.gardenia.viveroapp.Model.PersonId;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId> {

}
