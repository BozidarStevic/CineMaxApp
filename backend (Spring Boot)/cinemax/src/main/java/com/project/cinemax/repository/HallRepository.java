package com.project.cinemax.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
	
	List<Hall> findByCinemaId(Long cinemaId);

}
