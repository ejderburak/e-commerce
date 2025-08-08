package com.burakejder.repository;

import com.burakejder.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // finds products with category name (productCategory.categoryName)
    List<Product> findBtProductCategory_CategoryName(String categoryName);
}
