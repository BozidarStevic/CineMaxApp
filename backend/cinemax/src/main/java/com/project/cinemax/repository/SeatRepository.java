package com.project.cinemax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Seat;


public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByHallId(Long hallId);

}
