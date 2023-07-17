package com.project.cinemax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Cinema;
import com.project.cinemax.service.CinemaService;


@RestController
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	
	
	@DeleteMapping("/deleteCinema")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Cinema> deleteCinema(@RequestParam Long cinemaId) {
		
		cinemaService.deleteCinema(cinemaId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/updateCinema")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Cinema> updateCinema(
			@RequestParam Long cinemaId,
			@RequestParam String cinemaName) {
		
		cinemaService.updateCinema(cinemaId, cinemaName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/addCinema")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Cinema> addCinema(@RequestParam String cinemaName,
			@RequestParam Long cityId) {
		
		cinemaService.addCinema(cinemaName, cityId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/getCinemas")
	public ResponseEntity<List<Cinema>> getCinemas(@RequestParam Long cityId) {
		List<Cinema> cinemas = cinemaService.findCinemaByCity(cityId);
		return new ResponseEntity<>(cinemas, HttpStatus.OK);
	}

}
