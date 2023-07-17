package com.project.cinemax.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.User;


public interface UserRepository extends JpaRepository<User, String> {

	Page<User> findAll(Specification<User> specification, Pageable paging);
}
