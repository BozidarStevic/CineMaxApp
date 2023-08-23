package com.project.cinemax.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Hall;
import com.project.cinemax.model.Movie;
import com.project.cinemax.model.Projection;
import com.project.cinemax.repository.HallRepository;
import com.project.cinemax.repository.MovieRepository;
import com.project.cinemax.repository.ProjectionRepository;

@Service
public class ProjectionService {
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private InitService initService;
	
	
//	@Scheduled(fixedRate = 1000 * 60 * 60 * 12)
//	public void deleteOldProjections() {
//		Date oneDaysBefore = new Date(System.currentTimeMillis()-24*60*60*1000);
//		List<Projection> projections = projectionRepository.findByDateProjectionBefore(oneDaysBefore);
//		projectionRepository.deleteAll(projections);
//		System.out.println("Old projections deleted!  " + new Date().toString());
//	}
	
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24 * 7)
	public void reInitProjections() {
		projectionRepository.deleteAll();
		initService.initProjections();
		System.out.println("ReInit projections! (Scheduled)  " + new Date().toString());
	}
	
	public List<Projection> findAllProjections() {
		return projectionRepository.findAll();
	}
	
	public void deleteProjection(Long projectionId) {
		projectionRepository.deleteById(projectionId);
	}
	
	public void updateProjection(Long projectionId, Projection projection) {
		Projection existProjection = projectionRepository.findById(projectionId).orElse(null);
		if (existProjection != null) {
			existProjection.setDateProjection(projection.getDateProjection());
			existProjection.setPrice(projection.getPrice());
			existProjection.setStartTime(projection.getStartTime());
			Hall h = hallRepository.findById(projection.getHall().getId()).orElse(null);
			existProjection.setHall(h);
			Movie m = movieRepository.findById(projection.getMovie().getId()).orElse(null);
			existProjection.setMovie(m);
			
			projectionRepository.save(existProjection);
		}
	}
	
	public void addProjection(Projection projection) {
		projectionRepository.save(projection);
	}
	
	public List<Projection> getProjectionsByCinemaAndDateSortedByTime(Long cinemaId, Date date) {
		return projectionRepository.findByHallCinemaIdAndDateProjectionOrderByStartTimeAsc(cinemaId, (java.sql.Date) date);
	}
	
	public List<Projection> getProjectionsByMovieAndDate(Long movieId, Date date) {
		return projectionRepository.findByMovieIdAndDateProjection(movieId, (java.sql.Date) date);
	}

}
