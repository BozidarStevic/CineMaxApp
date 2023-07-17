package com.project.cinemax.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Projection;
import com.project.cinemax.model.Seat;
import com.project.cinemax.model.Ticket;
import com.project.cinemax.service.TicketService;

@RestController
public class TicketController {
	
	
	
	@Autowired
	private TicketService ticketService;
	
	
	@GetMapping({"/getTicketsForUser"})
    @PreAuthorize("hasRole('User') || hasRole('Admin')")
    public ResponseEntity<List<Ticket>> getTicketsForUser(
			@RequestParam String username) {
		
		List<Ticket> tickets = ticketService.getTicketsForUser(username);
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}
	
	@GetMapping("/ticketsForAdmin")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Page<Ticket>> getTickets(
			@RequestParam(required = false) String ownerFirstName,
			@RequestParam(required = false) String ownerLastName,
			@RequestParam(required = false) Date dateProjection,
			@RequestParam(required = false, defaultValue = "0") Long cinemaId,
			@RequestParam(required = false) String sortDateProjection,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "6") int size) 
	{	
		Page<Ticket> tickets = ticketService.getTicketsPageForAdmin(ownerFirstName, 
				ownerLastName, dateProjection, cinemaId, sortDateProjection, page, size);
		
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteTicket")
	@PreAuthorize("hasRole('User') || hasRole('Admin')")
	public ResponseEntity<Ticket> deleteTicket(@RequestParam Long ticketId) {
		
		ticketService.deleteTicket(ticketId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/getTickets")
	@PreAuthorize("hasRole('User') || hasRole('Admin')")
	public ResponseEntity<List<Ticket>> getTickets(
			@RequestParam Long projectionId) {
		
		List<Ticket> tickets = ticketService.getTicketsByProjection(projectionId);
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}
	
	
	@PostMapping("/bookTicket")
	@PreAuthorize("hasRole('User') || hasRole('Admin')")
	public ResponseEntity<Ticket> bookTicket(
			@RequestBody RecivedObjectforTicket recivedObject) {
		
		Ticket ticket = ticketService.bookTicket(recivedObject);
		
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	public static class RecivedObjectforTicket {
		public double price;
		public Projection projection;
		public Set<Seat> selectedSeats;
		public String userName;
		
		public RecivedObjectforTicket(double price, Projection projection, Set<Seat> selectedSeats, String userName) {
			super();
			this.price = price;
			this.projection = projection;
			this.selectedSeats = selectedSeats;
			this.userName = userName;
		}
	}

}
