package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Converters.BaseCostFactory;
import com.gardenia.viveroapp.DTO.BaseCostDTO;
import com.gardenia.viveroapp.Model.BaseCost;

import com.gardenia.viveroapp.Repository.BaseCostRepository;

@Service
public class BaseCostService {

    @Autowired
    private BaseCostRepository baseCostRepository;

    @Autowired
    private BaseCostFactory baseCostFactory;

    public List<BaseCostDTO> getAllBaseCost() {
        List<BaseCost> baseCosts = baseCostRepository.findAll();
        List<BaseCostDTO> baseCostDTOs = new ArrayList<>();
        for (int i = 0; i < baseCosts.size(); i++) {
            BaseCost baseCost = baseCosts.get(i);
            BaseCostDTO BaseCostDTO = baseCostFactory.toDTO(baseCost);
            baseCostDTOs.add(BaseCostDTO);
        }
        return baseCostDTOs;
    }

    public BaseCostDTO getAccountById(Integer id) {
        Optional<BaseCost> optionalAccount = baseCostRepository.findById(id);
        if (optionalAccount.isPresent()) {
            return baseCostFactory.toDTO(optionalAccount.get());
        } else {
            return null;
        }
    }

    public BaseCostDTO addBaseCost(BaseCostDTO baseCostDTO) {
        baseCostRepository.save(baseCostFactory.DTOToEntity(baseCostDTO));
        return baseCostDTO;
    }

    public void deleteBaseCostById(Integer id) {
        baseCostRepository.deleteById(id);
    }

    public BaseCostDTO updateBaseCost(BaseCostDTO baseCost) {
        Optional<BaseCost> optionalBaseCost = baseCostRepository.findById(baseCost.getIdbase_cost());
        if (optionalBaseCost.isPresent()) {
            BaseCost savedBaseCost = optionalBaseCost.get();
            savedBaseCost.setName(baseCost.getName());
            savedBaseCost.setAmount(baseCost.getAmount());
            baseCostRepository.save(savedBaseCost);
            return baseCost;
        } else {
            return null;
        }
    }
}
