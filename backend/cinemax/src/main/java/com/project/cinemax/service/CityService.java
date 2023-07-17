package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.City;
import com.project.cinemax.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findAllCities() {
		return cityRepository.findAll();
	}
	
	public void deleteCity(Long cityId) {
		cityRepository.deleteById(cityId);
	}
	
	public void updateCity(Long cityId, String cityName) {
		City existCity = cityRepository.findById(cityId).orElse(null);
		if (existCity != null) {
			existCity.setName(cityName);
			cityRepository.save(existCity);
		}
	}
	
	public void addCinema(String cityName) {
		City city = new City();
		city.setName(cityName);
		cityRepository.save(city);
	}

}
