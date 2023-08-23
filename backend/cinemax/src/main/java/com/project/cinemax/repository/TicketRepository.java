package com.project.cinemax.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByProjectionId(Long projectionId);

	List<Ticket> findByUserUserName(String userName);

	Page<Ticket> findAll(Specification<Ticket> specification, Pageable paging);

	List<Ticket> findByProjectionDateProjectionBefore(Date oneDaysBefore);

}
