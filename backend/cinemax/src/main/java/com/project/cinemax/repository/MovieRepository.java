package com.project.cinemax.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.project.cinemax.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
	
    public Page<Movie> findByTitleContaining(String title, Pageable paging);
	
	public List<Movie> findByTitleContaining(String title);

	public Page<Movie> findByCategoryId(Long categoryId, Pageable paging);

	public Page<Movie> findByTitleContainingAndCategoryId(String title, Long categoryId, Pageable paging);
	
	public Long countByTitle(String title);
	
	public List<Movie> findByTitleContainingAndCategoryId(String title, Long categoryId);
	
	public List<Movie> findAll(Specification<Movie> specification);
	
	public Page<Movie> findAll(Specification<Movie> specification, Pageable paging);
	
	public List<Movie> findTop10ByOrderByAverageRateDesc();
	
	public List<Movie> findByReleaseDate(int releaseDate);

	public List<Movie> findTop10ByOrderByReleaseDateDesc();
	
}
