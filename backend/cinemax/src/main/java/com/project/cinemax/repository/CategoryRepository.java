package com.project.cinemax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
