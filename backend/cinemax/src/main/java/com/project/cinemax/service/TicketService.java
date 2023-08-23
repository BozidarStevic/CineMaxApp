package com.project.cinemax.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.cinemax.controller.TicketController.RecivedObjectforTicket;
import com.project.cinemax.events.TicketDeletedEventPublisher;
import com.project.cinemax.model.Cinema;
import com.project.cinemax.model.Projection;
import com.project.cinemax.model.Seat;
import com.project.cinemax.model.Ticket;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.CinemaRepository;
import com.project.cinemax.repository.TicketRepository;
import com.project.cinemax.repository.UserRepository;
import com.project.cinemax.specification.TicketSpecification;
import org.springframework.context.ConfigurableApplicationContext;

@Service
public class TicketService {

	@Autowired
	private ConfigurableApplicationContext context;

	@Autowired
	private CinemaRepository cinemaRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 12)
	public void deleteOldTickets() {
		Date oneDaysBefore = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		List<Ticket> tickets = ticketRepository.findByProjectionDateProjectionBefore(oneDaysBefore);
		ticketRepository.deleteAll(tickets);
		System.out.println("Old tickets deleted! (Scheduled)  " + new Date().toString());
	}

	public List<Ticket> getTicketsForUser(String username) {
		return ticketRepository.findByUserUserName(username);
	}

	public Page<Ticket> getTicketsPageForAdmin(String ownerFirstName, String ownerLastName, Date dateProjection,
			Long cinemaId, String sortDateProjection, int page, int size) {

		Cinema cinema = cinemaRepository.findById(cinemaId).isPresent() ? cinemaRepository.findById(cinemaId).get() : null;
		Specification<Ticket> specification = TicketSpecification.getSpec(ownerFirstName, ownerLastName, cinema,
				dateProjection, sortDateProjection);
		Pageable paging = PageRequest.of(page, size);

		Page<Ticket> tickets = ticketRepository.findAll(specification, paging);
		return tickets;
	}

	public void deleteTicket(Long ticketId) {
		Ticket t = ticketRepository.findById(ticketId).get();
		Projection p = t.getProjection();
		Integer projectionId = p.getId().intValue();
		Set<Seat> seats = t.getSeats();

		TicketDeletedEventPublisher ticketDeletedEventPublisher = context.getBean(TicketDeletedEventPublisher.class);
		
		//Za brisanje rezervisanih sedista u singleton-u i na frontu putem websocket-a
		for (Seat seat : seats) {
			ticketDeletedEventPublisher.publishEvent(seat.getId().intValue(), projectionId);
		}
		ticketRepository.deleteById(ticketId);
	}

	public List<Ticket> getTicketsByProjection(Long projectionId) {
		return ticketRepository.findByProjectionId(projectionId);
	}

	public Ticket bookTicket(RecivedObjectforTicket recivedObject) {
		User user = userRepository.findById(recivedObject.userName).get();
		Ticket t = new Ticket(recivedObject.price, user, recivedObject.selectedSeats, recivedObject.projection);
		Ticket t1 = ticketRepository.save(t);
		return t1;
	}

}
