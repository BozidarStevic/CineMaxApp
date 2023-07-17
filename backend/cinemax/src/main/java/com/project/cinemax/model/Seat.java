package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int number;
	
	@ManyToOne
	@JsonIgnore
	private Hall hall;
	
	@ManyToMany(mappedBy = "seats")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Ticket> tickets;

	public Seat() {
		super();
	}

	public Seat(int number, Hall hall, Collection<Ticket> tickets) {
		super();
		this.number = number;
		this.hall = hall;
		this.tickets = tickets;
	}
	
	public Seat(int number, Hall hall) {
		super();
		this.number = number;
		this.hall = hall;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

}
