package com.project.cinemax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Role;
import com.project.cinemax.model.User;
import com.project.cinemax.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
//    @PostMapping({"/createNewRole"})
//    public Role createNewRole(@RequestBody Role role) {
//    	
//        return roleService.createNewRole(role);
//    }
    
    
    @GetMapping("/getRoles")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<Role>> getAllRoles() {
		
		List<Role> roles = roleService.getAllRoles();
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
    
    
    @GetMapping("/setNewRole")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<User> setNewRole(
			@RequestParam String roleName, 
			@RequestParam String userName) {
		
    	roleService.setNewRoleForUser(roleName, userName);
    	
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
}
