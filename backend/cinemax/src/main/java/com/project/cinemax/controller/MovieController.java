package com.project.cinemax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.project.cinemax.model.Movie;
import com.project.cinemax.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
		
	
	@GetMapping("/movies")
	public ResponseEntity<Page<Movie>> getMovies(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) Integer year,
			@RequestParam(required = false, defaultValue = "0") Long categoryId,
			@RequestParam(required = false) String sortReleaseDate,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "8") int size
	        ) {
		Page<Movie> movies = movieService.getMoviesPage(title, year, 
				categoryId, sortReleaseDate, page, size);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	
	@GetMapping("/allMovies")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<Movie>> getAllMovies() {
		
		List<Movie> movies = movieService.getAllMovies();
		
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	
	@GetMapping("/resetAverageRate")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Movie> resetAverageRate(@RequestParam Long movieId) {
		
		movieService.resetMovieAverageRate(movieId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteMovie")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Movie> deleteMovie(@RequestParam Long movieId) {
		
		movieService.deleteMovie(movieId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/updateMovie")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Movie> updateMovie(@RequestParam Long movieId, 
			@RequestBody Movie movie) {
		
		movieService.updateMovie(movieId, movie);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/addMovie")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		
		movieService.addMovie(movie);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/topRatedMovies")
	public ResponseEntity<List<Movie>> topRatedMovies() {
			List<Movie> movies = movieService.get10TopRatedMovies();
			return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	
	@GetMapping("/newMovies")
	public ResponseEntity<List<Movie>> newMovies() {
		List<Movie> movies = movieService.get10NewestMovies();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

}
