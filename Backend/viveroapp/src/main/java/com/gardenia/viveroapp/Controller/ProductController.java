package com.gardenia.viveroapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.ProductDTO;
import com.gardenia.viveroapp.Service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/catalogo")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProductsWithoutVariants() {
        return productService.getProductsWithoutVariants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO != null) {
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar") // POR PROBAR, FALTA BASE COST
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO entity) {
        ProductDTO productDTO = productService.updateProduct(entity);
        if (productDTO != null) {
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }
    }
}
