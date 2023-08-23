package com.project.cinemax.model;

import java.io.Serializable;

public class WSMessage  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer seatId;

	
	public WSMessage() {
	}

	public WSMessage(int seatId) {
		super();
		this.seatId = seatId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	
}
