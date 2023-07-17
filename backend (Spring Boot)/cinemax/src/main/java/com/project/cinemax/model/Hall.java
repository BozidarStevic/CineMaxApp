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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Hall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int seatNumber;
	
	@ManyToOne
//	@JsonProperty(access = Access.WRITE_ONLY)
	private Cinema cinema;
	
	@OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Seat> seats;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Collection<Projection> projections;

	public Hall() {
		super();
	}

	public Hall(Long id, String name, int seatNumber, Cinema cinema, Collection<Seat> seats,
			Collection<Projection> projections) {
		super();
		this.id = id;
		this.name = name;
		this.seatNumber = seatNumber;
		this.cinema = cinema;
		this.seats = seats;
		this.projections = projections;
	}
	
	public Hall(String name, int seatNumber, Cinema cinema) {
		super();
		this.name = name;
		this.seatNumber = seatNumber;
		this.cinema = cinema;
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

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Collection<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Collection<Seat> seats) {
		this.seats = seats;
	}

	public Collection<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Collection<Projection> projections) {
		this.projections = projections;
	}

}
