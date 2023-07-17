package com.project.cinemax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.User;
import com.project.cinemax.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        User user1 = userService.registerNewUser(user);
        if (user1 == null) {
        	return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    
    
    @GetMapping("/users")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) String userFirstName,
			@RequestParam(required = false) String userLastName,
			@RequestParam(required = false) String sortUserName,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) 
    {
		Page<User> users = userService.getFilteredUsersPage(userName, 
				userFirstName, userLastName, sortUserName, page, size);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
    
    
    @DeleteMapping("/deleteUser")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<User> deleteUser(@RequestParam String userName) {
		
    	userService.deleteUser(userName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

    
}
