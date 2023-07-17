package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double price;
	
	@ManyToOne
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER) //(, cascade = CascadeType.ALL)
    @JoinTable(name = "TICKET_SEAT",
            joinColumns = {
                    @JoinColumn(name = "TICKET_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "SEAT_ID")
            }
    )
    private Set<Seat> seats;
	
	@ManyToOne
	private Projection projection;

	public Ticket() {
		super();
	}

	public Ticket(double price, User user, Set<Seat> seats, Projection projection) {
		super();
		this.price = price;
		this.user = user;
		this.seats = seats;
		this.projection = projection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}
	
}
