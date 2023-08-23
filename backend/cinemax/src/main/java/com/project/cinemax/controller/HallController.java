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

import com.project.cinemax.model.Hall;
import com.project.cinemax.service.HallService;

@RestController
public class HallController {
	
	@Autowired
	private HallService hallService;
	
	
	@DeleteMapping("/deleteHall")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Hall> deleteHall(@RequestParam Long hallId) {
		
		hallService.deleteHall(hallId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/updateHall")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Hall> updateHall(
			@RequestParam Long hallId,
			@RequestParam String hallName) {
		
		hallService.updateHall(hallId, hallName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/addHall")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Hall> addHall(@RequestParam String hallName,
			@RequestParam Long cinemaId, @RequestParam Integer seatsNum) {
		
		hallService.addHall(hallName, cinemaId, seatsNum);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/getHalls")
	public ResponseEntity<List<Hall>> getHalls(@RequestParam Long cinemaId) {
		
		List<Hall> halls = hallService.getCinemaHalls(cinemaId);
		
		return new ResponseEntity<>(halls, HttpStatus.OK);
	}

}
