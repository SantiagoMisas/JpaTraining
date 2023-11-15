package com.jpa.JpaTraining.services;

import com.jpa.JpaTraining.entities.Category;
import com.jpa.JpaTraining.entities.Product;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SearchProductSpecification implements Specification<Product> {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String category;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(name)){
            Expression<String> productNameToLowerCase = criteriaBuilder.lower(root.get("name"));
            Predicate nameLikePredicate = criteriaBuilder.like(productNameToLowerCase, "%".concat(name.toLowerCase()).concat("%"));
            predicates.add(nameLikePredicate);
        }

        if(minPrice != null && !minPrice.equals(BigDecimal.ZERO) ){
            Predicate priceGreaterThanEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(priceGreaterThanEqualPredicate);
        }

        if(maxPrice != null && !maxPrice.equals(BigDecimal.ZERO) ){
            Predicate priceLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(priceLessThanEqualPredicate);
        }

        Join<Product, Category> productCategoryJoin = root.join("category");
        if(StringUtils.hasText(category)){
            Expression<String> categoryNameToLowerCase = criteriaBuilder.lower(productCategoryJoin.get("name"));
            Predicate categoryNameLikePredicate = criteriaBuilder.like(categoryNameToLowerCase, "%".concat(category.toLowerCase()).concat("%"));
            predicates.add(categoryNameLikePredicate);
        }

        query.orderBy(criteriaBuilder.asc(root.get("price")));
        return criteriaBuilder.and( predicates.toArray( new Predicate[predicates.size()] ) );
    }
}
