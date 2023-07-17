package com.project.cinemax.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.project.cinemax.model.User;

@Component
public class UserSpecification {
	
	public static Specification<User> getSpec(String userName, String userFirstName, String userLastName, String sortUserName) {
    	
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (userName != null && !userName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("userName"), "%" + userName + "%"));
            }
            if (userFirstName != null && !userFirstName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("userFirstName"), "%" + userFirstName+ "%"));
            }
            if (userLastName != null && !userLastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("userLastName"), "%" + userLastName+ "%"));
            }
            
            if (sortUserName != null && !sortUserName.isEmpty()) {
            	if (sortUserName.equals("asc"))
                	query.orderBy(criteriaBuilder.asc(root.get("userName")));
            	if (sortUserName.equals("desc")) 
            		query.orderBy(criteriaBuilder.desc(root.get("userName")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
