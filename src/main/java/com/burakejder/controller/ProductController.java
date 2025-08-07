package com.burakejder.controller;

import com.burakejder.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/rest/api/product")
public class ProductController {

    private final ProductService productService;

}

