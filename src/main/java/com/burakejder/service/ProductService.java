package com.burakejder.service;

import com.burakejder.DTO.DtoProduct;
import com.burakejder.DTO.DtoStockUpdate;
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
        if (productList.isEmpty()) {
            throw new IllegalArgumentException("No products found");
        }

        List<DtoProduct> dtoList = new ArrayList<>();

        for(Product product : productList){
            DtoProduct dtoProduct = DtoMapper.toDto(product); // turning into dtoProduct
            dtoList.add(dtoProduct);
        }
        return dtoList;
    }

    public DtoProduct findById(Long id){

        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()){
            throw new IllegalArgumentException("Product not found");
        }

        return DtoMapper.toDto(optional.get());
    }

    public DtoProduct addProduct(DtoProduct dtoProduct){

        Long categoryId = dtoProduct.getCategory().getCategoryId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            throw new IllegalArgumentException("Category not found, please enter a valid category");
        }

        Category dbCategory = optionalCategory.get();
        Product product = DtoMapper.toEntity(dtoProduct);

        product.setProductCategory(dbCategory);
        Product savedProduct = productRepository.save(product);

        return DtoMapper.toDto(savedProduct);
    }

    // updating the product
    public DtoProduct updateProduct(Long id, DtoProduct dtoProduct){

        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalArgumentException("Product not found");
        }

        Product product = optional.get();

        if (dtoProduct.getProductName() != null) {
            product.setProductName(dtoProduct.getProductName());
        }
        if (dtoProduct.getInventory() != null) {
            product.setInventory(dtoProduct.getInventory());
        }
        if (dtoProduct.getPrice() != null) {
            product.setPrice(dtoProduct.getPrice());
        }

        Long categoryId = dtoProduct.getCategory().getCategoryId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            throw new IllegalArgumentException("Category not found, please enter a valid category");
        }

        product.setProductCategory(optionalCategory.get());

        Product savedProduct = productRepository.save(product);

        return  DtoMapper.toDto(savedProduct);

    }

    // deleting a product
    public boolean deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
        return true;
    }

    public List<DtoProduct> findByCategory(String categoryName) {

        if (categoryName == null || categoryName.isEmpty()){
            throw new IllegalArgumentException("Category name is required");
        }

        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }

        List<Product> products = productRepository.findByProductCategory_CategoryName(categoryName);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found in this category");
        }
        List<DtoProduct> result = new ArrayList<>();
        for (Product p : products) {
            result.add(DtoMapper.toDto(p));
        }
        return result;
    }

    public DtoProduct updateStock(Long id, DtoStockUpdate stockUpdate) {

        Optional<Product> optional = productRepository.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }

        Product product = optional.get();
        product.setInventory(stockUpdate.getInventory());
        Product saved = productRepository.save(product);
        return DtoMapper.toDto(saved);
    }
}

