package com.burakejder.service;

import com.burakejder.DTO.DtoCategory;
import com.burakejder.DTO.DtoProduct;
import com.burakejder.entities.Category;
import com.burakejder.entities.Product;
import com.burakejder.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public DtoProduct findById(Long id){

        DtoProduct dtoProduct = new DtoProduct();
        DtoCategory dtoCategory = new DtoCategory();

        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }

        Product dbProduct = optional.get();
        Category dbCategory = optional.get().getProductCategory();

        BeanUtils.copyProperties(dbProduct,dtoProduct);
        BeanUtils.copyProperties(dbCategory,dtoCategory);

        dtoProduct.setProductCategory(dtoCategory);
        return dtoProduct;
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }


}
