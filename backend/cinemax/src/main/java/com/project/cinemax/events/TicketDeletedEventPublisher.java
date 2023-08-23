package com.project.cinemax.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TicketDeletedEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public TicketDeletedEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(Integer seatId, Integer projectionId) {
    	TicketDeletedEvent customEvent = new TicketDeletedEvent(this, seatId, projectionId);
        eventPublisher.publishEvent(customEvent);
    }
}