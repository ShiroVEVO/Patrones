package com.gardenia.viveroapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.PurchaseOrderDTO;
import com.gardenia.viveroapp.Service.PurchaseOrderService;

@RestController
@RequestMapping("/ordenes")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllAccounts() {
        return new ResponseEntity<>(purchaseOrderService.getAllPurchaseOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getOrderById(@PathVariable Integer id) {
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderService.getPurchaseOrderById(id);
        if (purchaseOrderDTO != null) {
            return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<PurchaseOrderDTO> addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return new ResponseEntity<>(purchaseOrderService.addPurchaseOrder(purchaseOrderDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        purchaseOrderService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PurchaseOrderDTO> updatePerson(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrderDTO purchaseOrderDTOUpdated = purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO);
        if (purchaseOrderDTOUpdated != null) {
            return new ResponseEntity<>(purchaseOrderDTOUpdated,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
