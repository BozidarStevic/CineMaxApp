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

import com.project.cinemax.model.City;
import com.project.cinemax.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities() {
		
		List<City> cities = cityService.findAllCities();
		
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteCity")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<City> deleteCity(@RequestParam Long cityId) {
		
		cityService.deleteCity(cityId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/updateCity")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<City> updateCity(
			@RequestParam Long cityId,
			@RequestParam String cityName) {
		
		cityService.updateCity(cityId, cityName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/addCity")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<City> addCity(@RequestParam String cityName) {
		
		cityService.addCinema(cityName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
