package com.project.cinemax.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Projection;
import com.project.cinemax.service.ProjectionService;

@RestController
public class ProjectionController {
	
	@Autowired
	private ProjectionService projectionService;
	
	
	@GetMapping("/allProjections")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<Projection>> getAllProjections() {
		
		List<Projection> projections = projectionService.findAllProjections();
		
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteProjection")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Projection> deleteProjection(@RequestParam Long projectionId) {
		
		projectionService.deleteProjection(projectionId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping("/updateProjection")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Projection> updateProjection(@RequestParam Long projectionId, 
			@RequestBody Projection projection) {
		
		projectionService.updateProjection(projectionId, projection);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/addProjection")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Projection> addProjection(@RequestBody Projection projection) {
		
		projectionService.addProjection(projection);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/getProjections")
	public ResponseEntity<List<Projection>> getProjections(
			@RequestParam Long cinemaId, 
			@RequestParam Date date) {
		
		List<Projection> projections = projectionService.getProjectionsByCinemaAndDateSortedByTime(cinemaId, date);
		
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}
	
	
	@GetMapping("/getProjectionsByMovieDate")
	public ResponseEntity<List<Projection>> getProjectionsForMovie(
			@RequestParam Long movieId, 
			@RequestParam Date date) {
		
		List<Projection> projections = projectionService.getProjectionsByMovieAndDate(movieId, date);
		
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}

}
