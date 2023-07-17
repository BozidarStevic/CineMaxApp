package com.project.cinemax.specification;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.project.cinemax.model.Cinema;
import com.project.cinemax.model.Ticket;

public class TicketSpecification {
	
	public static Specification<Ticket> getSpec(String ownerFirstName, String ownerLastName,
    		Cinema cinema, Date dateProjection, String sortDateProjection) {
    	
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (ownerFirstName != null && !ownerFirstName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("user").get("userFirstName"), "%" + ownerFirstName + "%"));
            }
            if (ownerLastName != null && !ownerLastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("user").get("userLastName"), "%" + ownerLastName+ "%"));
            }
            if (cinema != null) {
                predicates.add(criteriaBuilder.equal(root.get("projection").get("hall").get("cinema"), cinema));
            }
            if (dateProjection != null) {
            	predicates.add(criteriaBuilder.equal(root.get("projection").<Date>get("dateProjection").as(java.util.Date.class), dateProjection));
            }
            
            
            if (sortDateProjection != null && !sortDateProjection.isEmpty()) {
            	if (sortDateProjection.equals("asc"))
                	query.orderBy(criteriaBuilder.asc(root.get("projection").<Date>get("dateProjection").as(java.util.Date.class)));
            	if (sortDateProjection.equals("desc")) 
            		query.orderBy(criteriaBuilder.desc(root.get("projection").<Date>get("dateProjection").as(java.util.Date.class)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
