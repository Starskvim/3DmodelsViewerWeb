package com.example.modelsviewerweb.repositories.specifications;

import com.example.modelsviewerweb.entities.PrintModelWeb;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ModelSpecs {
    public static Specification<PrintModelWeb> modelNameContains (String word){
        return new Specification<PrintModelWeb>() {
            @Override
            public Predicate toPredicate(Root<PrintModelWeb> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("modelName")), "%"+word.toUpperCase()+"%");
            }
        };
    }

    public static Specification<PrintModelWeb> modelCategoryContains (String word){
        return (Specification<PrintModelWeb>) (root, query, criteriaBuilder) -> {return criteriaBuilder.like(root.get("modelCategory"), "%"+word+"%");};
    }

}
