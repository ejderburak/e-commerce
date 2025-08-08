package com.burakejder.service;

import com.burakejder.DTO.DtoProduct;
import com.burakejder.entities.Category;
import com.burakejder.entities.Product;
import com.burakejder.mapper.DtoMapper;
import com.burakejder.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<DtoProduct> findAll() {

        // getting all products from database
        List<Product> productList = productRepository.findAll();

        List<DtoProduct> dtoList = new ArrayList<>();

        for(Product product : productList){
            DtoProduct dtoProduct = DtoMapper.toDto(product); // turning into dtoProduct
            dtoList.add(dtoProduct);
        }
        return dtoList;
    }

    public DtoProduct findById(Long id){
        Optional<Product> optional = productRepository.findById(id);

        if(optional.isEmpty()){ return null; }

        return DtoMapper.toDto(optional.get());
    }

    public DtoProduct addProduct(DtoProduct dtoProduct){

        //first convert dto ->  entity
        Product product = DtoMapper.toEntity(dtoProduct);

        // saving to db
        Product dbProduct = productRepository.save(product);

        // returning the saved proudct to dto
        return DtoMapper.toDto(dbProduct);


    }
}
