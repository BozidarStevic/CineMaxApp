package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Cinema;
import com.project.cinemax.model.City;
import com.project.cinemax.repository.CinemaRepository;
import com.project.cinemax.repository.CityRepository;

@Service
public class CinemaService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	
	public void deleteCinema(Long cinemaId) {
		cinemaRepository.deleteById(cinemaId);
	}
	
	public void updateCinema(Long cinemaId, String cinemaName) {
		Cinema existCinema = cinemaRepository.findById(cinemaId).orElse(null);
		if (existCinema != null) {
			existCinema.setName(cinemaName);
			cinemaRepository.save(existCinema);
		}
	}
	
	public void addCinema(String cinemaName, Long cityId) {
		Cinema cinema = new Cinema();
		cinema.setName(cinemaName);
		City city = cityRepository.findById(cityId).orElse(null);
		cinema.setCity(city);
		cinemaRepository.save(cinema);
	}
	
	public List<Cinema> findCinemaByCity(Long cityId) {
		return cinemaRepository.findByCityId(cityId);
	}
	
}
