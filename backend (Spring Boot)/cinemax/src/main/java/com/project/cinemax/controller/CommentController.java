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

import com.project.cinemax.model.Comment;
import com.project.cinemax.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@DeleteMapping("/deleteComment")
	@PreAuthorize("hasRole('User') || hasRole('Admin')")
	public ResponseEntity<Comment> deleteComment(@RequestParam Long commentId) {
		
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/addComment")
	public ResponseEntity<Comment> addComment(
			@RequestParam String commentText,
			@RequestParam Long movieId,
			@RequestParam String userName) {
		
		commentService.addComment(commentText, movieId, userName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getComments")
	public ResponseEntity<List<Comment>> getComments(@RequestParam Long movieId) {
		List<Comment> comments = commentService.getMovieComments(movieId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

}
