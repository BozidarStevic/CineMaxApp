package com.project.cinemax.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Projection;


public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	// List<Projection> findByHallCinemaIdAndDateProjection(Long cinemaId, Date dateProjection);
	
	List<Projection> findByMovieIdAndDateProjection(Long cinemaId, Date dateProjection);
	
	List<Projection> findByDateProjectionBefore(java.util.Date date);

	List<Projection> findByHallCinemaIdAndDateProjectionOrderByStartTimeAsc(Long cinemaId, Date date);
	
}
