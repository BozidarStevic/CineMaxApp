package com.project.cinemax.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Projection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dateProjection;
	
	private double price;
	
	private Time startTime;
	
	@ManyToOne
//	@JsonProperty(access=Access.WRITE_ONLY)
	private Hall hall;
	
	@ManyToOne
	private Movie movie;
	
	@OneToMany(mappedBy = "projection", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Ticket> tickets;
	
	

	public Projection() {
		super();
	}

	public Projection(Date dateProjection, double price, Time startTime, Hall hall, Movie movie, Collection<Ticket> tickets) {
		super();
		this.dateProjection = dateProjection;
		this.price = price;
		this.startTime = startTime;
		this.hall = hall;
		this.movie = movie;
		this.tickets = tickets;
	}
	
	public Projection(Date dateProjection, double price, Time startTime, Hall hall, Movie movie) {
		super();
		this.dateProjection = dateProjection;
		this.price = price;
		this.startTime = startTime;
		this.hall = hall;
		this.movie = movie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateProjection() {
		return dateProjection;
	}

	public void setDateProjection(Date dateProjection) {
		this.dateProjection = dateProjection;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

}
