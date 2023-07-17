package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cinema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private Collection<Hall> halls;
	
	@ManyToOne
	private City city;

	public Cinema() {
		super();
	}

	public Cinema(String name, Collection<Hall> halls, City city) {
		super();
		this.name = name;
		this.halls = halls;
		this.city = city;
	}
	
	public Cinema(String name, City city) {
		super();
		this.name = name;
		this.city = city;
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

	public Collection<Hall> gethalls() {
		return halls;
	}

	public void sethalls(Collection<Hall> halls) {
		this.halls = halls;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


}
