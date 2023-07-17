package com.project.cinemax.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Category;
import com.project.cinemax.model.Movie;
import com.project.cinemax.model.Rate;
import com.project.cinemax.repository.CategoryRepository;
import com.project.cinemax.repository.MovieRepository;
import com.project.cinemax.repository.RateRepository;
import com.project.cinemax.specification.MovieSpecification;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RateRepository rateRepository;
	
	
	public Page<Movie> getMoviesPage(String title, Integer year, 
		Long categoryId, String sortReleaseDate, int page, int size) {
		
		LocalDate dateFrom = null;
		LocalDate dateTo = null;
		if (year != null) {
			dateFrom = LocalDate.of(year, 1, 1);
			dateTo = LocalDate.of(year, 12, 31);
		}
		Category category = categoryRepository.findById(categoryId).isPresent() ?
				categoryRepository.findById(categoryId).get() : null;
		Specification<Movie> specification = MovieSpecification.getSpec(title, category, dateFrom, dateTo, sortReleaseDate);
		Pageable paging = PageRequest.of(page, size);
		
		Page<Movie> movies = movieRepository.findAll(specification, paging);
		return movies;
	}
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public void resetMovieAverageRate(Long movieId) {
		Movie m = movieRepository.findById(movieId).orElse(null);
		if (m != null) {
			m.setAverageRate(0);
		}
		List<Rate> rates = rateRepository.findByMovieId(movieId);
		if (rates.size() > 0) {
			for (Rate rate : rates) {
				rateRepository.delete(rate);
			}
		}
		movieRepository.save(m);
	}
	
	public void deleteMovie(Long movieId) {
		movieRepository.deleteById(movieId);
	}
	
	public void updateMovie(Long movieId, Movie movie) {
		Movie existMovie = movieRepository.findById(movieId).orElse(null);
		if (existMovie != null) {
			existMovie.setTitle(movie.getTitle());
			existMovie.setPhoto(movie.getPhoto());
			existMovie.setDescription(movie.getDescription());
			existMovie.setDuration(movie.getDuration());
			existMovie.setDirector(movie.getDirector());
			existMovie.setReleaseDate(movie.getReleaseDate());
			Category c = categoryRepository.findById(movie.getCategory().getId()).orElse(null);
			existMovie.setCategory(c);
			
			movieRepository.save(existMovie);
		}
	}
	
	public void addMovie(Movie movie) {
		movie.setAverageRate(0);
		movieRepository.save(movie);
	}
	
	public List<Movie> get10TopRatedMovies() {
		return movieRepository.findTop10ByOrderByAverageRateDesc();
	}
	
	public List<Movie> get10NewestMovies() {
		return movieRepository.findTop10ByOrderByReleaseDateDesc();
	}
}
