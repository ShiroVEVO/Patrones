package com.gardenia.viveroapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.PersonDTO;
import com.gardenia.viveroapp.Service.PersonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/personas")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return new ResponseEntity<List<PersonDTO>>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<PersonDTO> getPersonById(@RequestParam String tipo, @RequestParam Integer numero) {
        PersonDTO person = personService.getPersonById(tipo, numero);
        if (person != null) {
            return new ResponseEntity<PersonDTO>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.addPerson(personDTO), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deletePerson(@RequestParam String tipo, @RequestParam Integer numero) {
        personService.deletePerson(tipo, numero);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        PersonDTO person = personService.updatePerson(personDTO);
        if (person != null) {
            return new ResponseEntity<>(personService.updatePerson(personDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
