package com.project.cinemax.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Comment;
import com.project.cinemax.model.Movie;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.CommentRepository;
import com.project.cinemax.repository.MovieRepository;
import com.project.cinemax.repository.UserRepository;

@Service
public class CommentService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
	
	public void addComment(String commentText, Long movieId, String userName) {
		Movie movie = movieRepository.findById(movieId).get();
		User user = userRepository.findById(userName).get();
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		Comment comment = new Comment(commentText, date, user, movie);
		commentRepository.save(comment);
	}
	
	public List<Comment> getMovieComments(Long movieId) {
		List<Comment> comments = commentRepository.findByMovieId(movieId);
		for (Comment c : comments) {
			c.getUser().setUserPassword("");
		}
		return comments;
	}

}
