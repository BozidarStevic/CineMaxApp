package com.project.cinemax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Movie;
import com.project.cinemax.service.RateService;

@RestController
public class RateController {
	
	@Autowired
	private RateService rateService;
	
	
	@GetMapping("/rateMovie")
	public ResponseEntity<Movie> rateMovie(
			@RequestParam Integer rate,
			@RequestParam Long movieId,
			@RequestParam String userName)
	{
		Movie movie = rateService.rateMovie(rate, movieId, userName);
		
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	
	@GetMapping("/isRated")
	public ResponseEntity<Boolean> isRated(
			@RequestParam Long movieId,
			@RequestParam String userName)
	{
		Boolean isRated = rateService.isMovieRatedByUser(movieId, userName);
		
		return new ResponseEntity<>(isRated, HttpStatus.OK);
	}
	
}
