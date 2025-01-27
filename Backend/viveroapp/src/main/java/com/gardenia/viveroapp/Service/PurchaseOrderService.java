package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Converters.PurchaseOrderFactory;
import com.gardenia.viveroapp.DTO.PurchaseOrderDTO;
import com.gardenia.viveroapp.Model.PurchaseOrder;
import com.gardenia.viveroapp.Model.Person;
import com.gardenia.viveroapp.Repository.PersonRepository;
import com.gardenia.viveroapp.Repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PurchaseOrderFactory purchaseOrderFactory;

    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        List<PurchaseOrderDTO> ordersDTO = new ArrayList<PurchaseOrderDTO>();
        List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
        for (int i = 0; i < orders.size(); i++) {
            PurchaseOrder order = orders.get(i);
            PurchaseOrderDTO orderDTO = purchaseOrderFactory.toDTO(order);
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }

    public PurchaseOrderDTO getPurchaseOrderById(Integer id) {
        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById(id);
        if (optionalPurchaseOrder.isPresent()) {
            PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
            PurchaseOrderDTO purchaseOrderDTO = purchaseOrderFactory.toDTO(purchaseOrder);
            return purchaseOrderDTO;
        } else {
            return null;
        }
    }

    public PurchaseOrderDTO addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Optional<Person> optionalPerson = personRepository.findById(purchaseOrderDTO.getPersonId());
        if (optionalPerson.isPresent()) {
            PurchaseOrder purchaseOrder = purchaseOrderFactory.DTOToEntity(purchaseOrderDTO);
            Person person = optionalPerson.get();

            purchaseOrder.setPerson(person);
            purchaseOrderRepository.save(purchaseOrder);
            return purchaseOrderDTO;
        } else {
            return null;
        }

    }

    public void deleteAccount(Integer id) {
        purchaseOrderRepository.deleteById(id);
    }

    public PurchaseOrderDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById(purchaseOrderDTO.getIdorder());
        if (optionalPurchaseOrder.isPresent()) {
            PurchaseOrder savedPurchaseOrder = optionalPurchaseOrder.get();
            savedPurchaseOrder.setDate(purchaseOrderDTO.getDate());
            savedPurchaseOrder.setAmount(purchaseOrderDTO.getAmount());
            savedPurchaseOrder.setState(purchaseOrderDTO.getState());
            Optional<Person> optionalPerson = personRepository.findById(purchaseOrderDTO.getPersonId());
            if (optionalPerson.isPresent()) {
                savedPurchaseOrder.setPerson(optionalPerson.get());
            }
            purchaseOrderRepository.save(savedPurchaseOrder);
            return purchaseOrderDTO;
        } else {
            return null;
        }
    }
}
