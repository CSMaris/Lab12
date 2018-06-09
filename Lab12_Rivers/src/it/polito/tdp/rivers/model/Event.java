package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	
	EventType tipo;
	LocalDate data;
	Flow flow;
	
	public Event(EventType tipo, LocalDate data, Flow flow) {
	
		this.tipo = tipo;
		this.data = data;
		this.flow = flow;
	}

	@Override
	public int compareTo(Event event0) {
		
		return this.data.compareTo(event0.data);
	}

	public EventType getTipo() {
		return tipo;
	}

	public LocalDate getData() {
		return data;
	}

	public Flow getFlow() {
		return flow;
	}
	
	
	

}
