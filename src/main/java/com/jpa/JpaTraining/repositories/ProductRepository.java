package com.jpa.JpaTraining.repositories;

import com.jpa.JpaTraining.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByNameContainingIgnoreCaseAndPriceLessThanEqualAndPriceGreaterThanEqualAndCategoryNameContainingIgnoreCase(String name, BigDecimal minPrice, BigDecimal maxPrice, String categoryName);
    List<Product> findAllByPriceLessThanEqual(BigDecimal maxPrice);
    List<Product> findAllByNameContainingIgnoreCase(String name);
    List<Product> findAllByNameContainingIgnoreCaseAndCategoryNameContainingIgnoreCase(String name, String categoryName);
    List<Product> findAllByPriceLessThanEqualAndPriceGreaterThanEqual(BigDecimal maxPrice, BigDecimal minPrice);
    List<Product> findAllByNameContainingIgnoreCaseAndCategoryNameIgnoreCaseAndPriceLessThanEqual(String name, String categoryName, BigDecimal maxPrice);
    List<Product> findAllByNameContainingIgnoreCaseAndCategoryNameIgnoreCaseAndPriceGreaterThanEqual(String name, String categoryName, BigDecimal minPrice);
}
