package com.project.cinemax.events;

import org.springframework.context.ApplicationEvent;


public class TicketDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;

	private final Integer seatId;
	private final Integer projectionId;

    public TicketDeletedEvent(Object source, Integer seatId, Integer projectionId) {
        super(source);
        this.seatId = seatId;
        this.projectionId = projectionId;
    }

    public Integer getSeatId() {
        return seatId;
    }
    
    public Integer getProjectionId() {
        return projectionId;
    }
    
}