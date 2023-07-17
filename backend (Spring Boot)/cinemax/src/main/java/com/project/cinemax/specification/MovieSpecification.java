package com.project.cinemax.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.project.cinemax.model.Category;
import com.project.cinemax.model.Movie;


@Component
public class MovieSpecification {

    public static Specification<Movie> getSpec(String title,
    		Category category, LocalDate dateFrom, LocalDate dateTo, String sortReleaseDate) {
    	
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title+ "%"));
            }
            if (category != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }
            if (dateFrom != null && dateTo != null) {
            	predicates.add(criteriaBuilder.between(root.<LocalDate>get("releaseDate").as(java.time.LocalDate.class), dateFrom, dateTo));
            }
            if (sortReleaseDate != null && !sortReleaseDate.isEmpty()) {
            	if (sortReleaseDate.equals("asc"))
                	query.orderBy(criteriaBuilder.asc(root.<LocalDate>get("releaseDate").as(java.time.LocalDate.class)));
            	if (sortReleaseDate.equals("desc")) 
            		query.orderBy(criteriaBuilder.desc(root.<LocalDate>get("releaseDate").as(java.time.LocalDate.class)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
