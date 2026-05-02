package com.semihkurucay.specification;

import com.semihkurucay.entity.AuctionItem;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AuctionItemSpecification {

    public static Specification<AuctionItem> pageFilterAuctionItem(Long categoryId, String search, Boolean isActive, Long createdUserId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(categoryId != null && categoryId > -1) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
            }

            if(search != null && !search.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + search + "%"));
            }

            if(Boolean.TRUE.equals(isActive)){
                predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            }

            if(createdUserId != null && createdUserId > -1) {
                predicates.add(criteriaBuilder.equal(root.get("createdUser").get("id"), createdUserId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
