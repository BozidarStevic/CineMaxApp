package com.project.cinemax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Seat;
import com.project.cinemax.service.SeatService;

@RestController
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	
	@GetMapping("/getSeats")
	public ResponseEntity<List<Seat>> getSeats(
			@RequestParam Long hallId) {
		
		List<Seat> seats = seatService.getSeatsForHall(hallId);
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}
	
	@GetMapping("/getCurrentlySelectedSeats")
	public ResponseEntity<ArrayList<Integer>> getCurrentlySelectedSeats(
			@RequestParam Integer projectionId) {
		
		ArrayList<Integer> seats = seatService.getCurrentlySelectedSeats(projectionId);
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}

}
