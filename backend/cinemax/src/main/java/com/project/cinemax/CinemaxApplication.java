package com.project.cinemax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.JavaVersion;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.project.cinemax.service.InitService;

@SpringBootApplication
@EnableScheduling
public class CinemaxApplication implements CommandLineRunner {
	
	@Autowired
	private InitService initService;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaxApplication.class, args);
		System.out.println("Spring version: " + SpringVersion.getVersion());
		System.out.println("SpringBoot version: " + SpringBootVersion.getVersion());
		System.out.println("Java version: " + JavaVersion.getJavaVersion());
		
	}

	@Override
	public void run(String... args) throws Exception {
		initService.initCategories();
		initService.initMovies();
		initService.initCities();
		initService.initCinemas();
		initService.initHalls();
		initService.initSeats();
		initService.initProjections();
		initService.initRoles();
		initService.initUsers();
	}
	
}
