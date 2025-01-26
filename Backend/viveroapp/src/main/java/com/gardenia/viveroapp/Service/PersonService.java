package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Model.Person;
import com.gardenia.viveroapp.Model.PersonId;
import com.gardenia.viveroapp.Model.DTO.PersonDTO;

import com.gardenia.viveroapp.Repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = personRepository.findAll();
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            PersonDTO personDTO = PersonDTO.builder()
                    .docNumber(person.getId().getDoc_number())
                    .docType(person.getId().getDoc_type())
                    .name(person.getName())
                    .lastname(person.getLastname())
                    .phoneNumber(person.getPhone_number()).build();
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    public PersonDTO getPersonById(String tipo, Integer numero) {
        PersonId personid = new PersonId(tipo, numero);
        Optional<Person> optionalPerson = personRepository.findById(personid);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            PersonDTO personDTO = PersonDTO.builder()
                    .docNumber(person.getId().getDoc_number())
                    .docType(person.getId().getDoc_type())
                    .name(person.getName())
                    .lastname(person.getLastname())
                    .phoneNumber(person.getPhone_number())
                    .build();
            return personDTO;
        } else {
            return null;
        }
    }

    // VALIDAR QUE NO HAYA SIDO AGREGADA YA LA PERSONA CON ESE ID
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonId personid = new PersonId(personDTO.getDocType(), personDTO.getDocNumber());
        Person person = new Person(personid, personDTO.getName(), personDTO.getLastname(), personDTO.getPhoneNumber());
        personRepository.save(person);
        return personDTO;
    }

    public void deletePerson(String tipo, Integer numero) {
        PersonId personId = new PersonId(tipo, numero);
        personRepository.deleteById(personId);
    }

    public PersonDTO updatePerson(PersonDTO personDTO) {
        PersonId personId = new PersonId(personDTO.getDocType(), personDTO.getDocNumber());
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if (optionalPerson.isPresent()) {
            Person savedPerson = optionalPerson.get();
            savedPerson.setId(personId);
            savedPerson.setName(personDTO.getName());
            savedPerson.setLastname(personDTO.getLastname());
            savedPerson.setPhone_number(personDTO.getPhoneNumber());
            savedPerson = personRepository.save(savedPerson);
            PersonDTO newPerson = PersonDTO.builder()
                    .docNumber(personDTO.getDocNumber())
                    .docType(personDTO.getDocType())
                    .name(savedPerson
                            .getName())
                    .lastname(savedPerson.getLastname())
                    .phoneNumber(savedPerson.getPhone_number()).build();
            return newPerson;
        } else {
            return null;
        }
    }
}
