package com.project.cinemax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cinemax.model.City;


public interface CityRepository extends JpaRepository<City, Long> {

}
