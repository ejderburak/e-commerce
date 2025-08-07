package com.burakejder.service;

import com.burakejder.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

}
