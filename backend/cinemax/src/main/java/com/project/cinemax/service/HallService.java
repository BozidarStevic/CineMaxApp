package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Cinema;
import com.project.cinemax.model.Hall;
import com.project.cinemax.model.Seat;
import com.project.cinemax.repository.CinemaRepository;
import com.project.cinemax.repository.HallRepository;
import com.project.cinemax.repository.SeatRepository;

@Service
public class HallService {
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	public void deleteHall(Long hallId) {
		hallRepository.deleteById(hallId);
	}
	
	public void updateHall(Long hallId, String hallName) {
		Hall existHall = hallRepository.findById(hallId).orElse(null);
		if (existHall != null) {
			existHall.setName(hallName);
			hallRepository.save(existHall);
		}
	}
	
	public void addHall(String hallName, Long cinemaId, Integer seatsNum) {
		Hall hall = new Hall();
		hall.setName(hallName);
		hall.setSeatNumber(seatsNum);
		Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);
		hall.setCinema(cinema);
		
		Hall h = hallRepository.save(hall);
		
		for (int i = 0; i < seatsNum; i++) {
			Seat s = new Seat(i+1, h);
			seatRepository.save(s);
		}
	}
	
	public List<Hall> getCinemaHalls(Long cinemaId) {
		return hallRepository.findByCinemaId(cinemaId);
	}

}
