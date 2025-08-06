package com.burakejder.controller;

import com.burakejder.entities.Product;
import com.burakejder.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/rest/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/list")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(path = "/save")
    public void save(@RequestBody Product product) {
         productService.addProduct(product);
    }



}

