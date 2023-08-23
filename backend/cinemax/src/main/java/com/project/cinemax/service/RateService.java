package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Movie;
import com.project.cinemax.model.Rate;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.MovieRepository;
import com.project.cinemax.repository.RateRepository;
import com.project.cinemax.repository.UserRepository;

@Service
public class RateService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RateRepository rateRepository;
	
	
	public Movie rateMovie(Integer rate, Long movieId, String userName) {
		Movie movie = movieRepository.findById(movieId).get();
		User user = userRepository.findById(userName).get();
		Rate rate1 = new Rate(rate, user, movie);
		rateRepository.save(rate1);
		
		List<Rate> ratesList = rateRepository.findByMovieId(movieId);
		
		double ratesCount = ratesList.size();
		
		double ratesSum = 0;
		for (Rate currRate : ratesList) {
			ratesSum += currRate.getRate();
		}
		double newAvgRate = 0;
		if (ratesCount != 0) {
			newAvgRate = ratesSum / ratesCount;
			newAvgRate = Math.round(newAvgRate * 100.0) / 100.0;
		}
		movie.setAverageRate(newAvgRate);
		movie = movieRepository.save(movie);
		return movie;
	}
	
	public Boolean isMovieRatedByUser(Long movieId, String userName) {
		List<Rate> list = rateRepository.findByMovieIdAndUserUserName(movieId, userName);
		Boolean isRated = false;
		if (!list.isEmpty()) {
			isRated = true;
		}
		return isRated;
	}

}
