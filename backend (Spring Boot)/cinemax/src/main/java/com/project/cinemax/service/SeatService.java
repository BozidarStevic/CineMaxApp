package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Seat;
import com.project.cinemax.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	
	public List<Seat> getSeatsForHall(Long hallId) {
		return seatRepository.findByHallId(hallId);
	}

}
