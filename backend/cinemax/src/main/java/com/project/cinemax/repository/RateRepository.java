package com.project.cinemax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.cinemax.model.Rate;


public interface RateRepository  extends JpaRepository<Rate, Long>, JpaSpecificationExecutor<Rate> {
	
	public List<Rate> findByMovieId(Long movieId);
	public List<Rate> findByMovieIdAndUserUserName(Long movieId, String userUserName);
}
