package com.burakejder.controller;

import com.burakejder.DTO.DtoProduct;
import com.burakejder.service.ProductService;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<DtoProduct> findAll(){
        return productService.findAll();
    }

    @GetMapping(path = "/{id}")
    public DtoProduct findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    public DtoProduct addProduct(@RequestBody DtoProduct dtoProduct){
        return productService.addProduct(dtoProduct);
    }

}

