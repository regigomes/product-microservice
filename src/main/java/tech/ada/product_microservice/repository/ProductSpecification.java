package tech.ada.product_microservice.repository;

import org.springframework.data.jpa.domain.Specification;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> filterBy(String description, BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (description != null && !description.isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            return predicates;
        };
    }
}

