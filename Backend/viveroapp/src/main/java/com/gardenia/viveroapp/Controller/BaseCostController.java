package com.gardenia.viveroapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.BaseCostDTO;
import com.gardenia.viveroapp.Service.BaseCostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/costos_base")
public class BaseCostController {

    @Autowired
    private BaseCostService baseCostService;

    @GetMapping
    public ResponseEntity<List<BaseCostDTO>> getAllBaseCostDTO() {
        return new ResponseEntity<List<BaseCostDTO>>(baseCostService.getAllBaseCost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseCostDTO> getBaseCostById(@PathVariable Integer id) {
        BaseCostDTO baseCostDTO = baseCostService.getAccountById(id);
        if (baseCostDTO != null) {
            return new ResponseEntity<BaseCostDTO>(baseCostDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<BaseCostDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<BaseCostDTO> addBaseCostDTO(@RequestBody BaseCostDTO baseCostDTO) {
        return new ResponseEntity<BaseCostDTO>(baseCostService.addBaseCost(baseCostDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBaseCostDTOById(@PathVariable Integer id) {
        baseCostService.deleteBaseCostById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BaseCostDTO> updateBaseCostDTO(@RequestBody BaseCostDTO baseCostDTO) {
        BaseCostDTO entity = baseCostService.updateBaseCost(baseCostDTO);
        if (entity != null) {
            return new ResponseEntity<BaseCostDTO>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<BaseCostDTO>(HttpStatus.NOT_FOUND);
        }
    }
}
