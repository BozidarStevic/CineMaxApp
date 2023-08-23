package com.project.cinemax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Seat;
import com.project.cinemax.repository.SeatRepository;
import com.project.cinemax.util.SelectedSeatsSingleton;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	
	public List<Seat> getSeatsForHall(Long hallId) {
		return seatRepository.findByHallId(hallId);
	}


	public ArrayList<Integer> getCurrentlySelectedSeats(Integer projectionId) {
		SelectedSeatsSingleton selectedSeatsSingleton = SelectedSeatsSingleton.getInstance();
		ArrayList<Integer> seats = selectedSeatsSingleton.getMap().get(projectionId);
		return seats;
	}

}
