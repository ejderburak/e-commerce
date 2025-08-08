package com.burakejder.service;

import com.burakejder.DTO.DtoProduct;
import com.burakejder.entities.Category;
import com.burakejder.entities.Product;
import com.burakejder.mapper.DtoMapper;
import com.burakejder.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

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

        Long categoryId = dtoProduct.getCategory().getCategoryId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        Category dbCategory = optionalCategory.get();
        Product product = DtoMapper.toEntity(dtoProduct);

        product.setProductCategory(dbCategory);
        Product savedProduct = productRepository.save(product);

        return DtoMapper.toDto(savedProduct);
    }
}
