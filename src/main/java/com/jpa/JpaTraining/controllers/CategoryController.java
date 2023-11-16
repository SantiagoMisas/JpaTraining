package com.jpa.JpaTraining.controllers;

import com.jpa.JpaTraining.entities.Category;
import com.jpa.JpaTraining.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
 @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/v0")
    public List<Category> findAllv0(){
        return categoryRepository.findAll();
    }

    @GetMapping("/v1")
    public List<Category> findAllv1(){

        Specification<Category> categoryWithoutProductsSpecification = (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isEmpty(root.get("products"));
        };

        return categoryRepository.findAll(categoryWithoutProductsSpecification);
    }
}
