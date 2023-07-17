package com.project.cinemax.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Role;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.RoleRepository;
import com.project.cinemax.repository.UserRepository;

@Service
public class RoleService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	

//    public Role createNewRole(Role role) {
//        return roleRepository.save(role);
//    }
    
    public List<Role> getAllRoles() {
    	return roleRepository.findAll();
    }
    
    public void setNewRoleForUser(String roleName, String userName) {
    	Role role = roleRepository.findById(roleName).orElse(null);
		if (role != null) {
			User user = userRepository.findById(userName).orElse(null);
			if (user != null) {
				user.setRoles(new HashSet<Role>());
				user.addRole(role);
				
				userRepository.save(user);
				roleRepository.save(role);
			}
		}
    }
    
}
