package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private Collection<Cinema> cinemas;

	public City() {
		super();
	}

	public City(Long id, String name, Collection<Cinema> cinemas) {
		super();
		this.id = id;
		this.name = name;
		this.cinemas = cinemas;
	}

	public City(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(Collection<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

}
