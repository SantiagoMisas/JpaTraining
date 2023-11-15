package com.jpa.JpaTraining.controllers;


import com.jpa.JpaTraining.entities.Product;
import com.jpa.JpaTraining.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/v1")
    public List<Product> findAllv1(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) BigDecimal minPrice,
                                   @RequestParam(required = false) BigDecimal maxPrice,
                                   @RequestParam(required = false) String category)
    {
        System.out.println("V1");

        return null;
    }

}
