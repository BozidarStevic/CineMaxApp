package com.project.cinemax.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Role;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.RoleRepository;
import com.project.cinemax.repository.UserRepository;
import com.project.cinemax.specification.UserSpecification;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User user) {
    	User u = userRepository.findById(user.getUserName()).orElse(null);

    	if (u == null) {
    		Role role = roleRepository.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRoles(userRoles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));

            return userRepository.save(user);
    	} else {
    	    System.out.println("User already exists. Ignoring the save operation.");
    	    return null;
    	}
    }
    
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    public Page<User> getFilteredUsersPage(String userName, String userFirstName,
    		String userLastName, String sortUserName, int page, int size) {
    	
    	Specification<User> specification = UserSpecification.getSpec(userName, userFirstName, userLastName, sortUserName);
		Pageable paging = PageRequest.of(page, size);
		Page<User> users = userRepository.findAll(specification, paging);
		return users;
    }
    
    public void deleteUser(String userName) {
    	userRepository.deleteById(userName);
    }
    
}
