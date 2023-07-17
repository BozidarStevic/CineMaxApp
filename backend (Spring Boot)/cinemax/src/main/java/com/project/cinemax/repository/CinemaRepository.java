package com.project.cinemax.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Cinema;


public interface CinemaRepository extends JpaRepository<Cinema, Long> {

	List<Cinema> findByCityId(Long cityId);

}
