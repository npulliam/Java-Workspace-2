package com.nathanpulliam.beltreviewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanpulliam.beltreviewer.models.Event;
import com.nathanpulliam.beltreviewer.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	
	public Event saveEvent(Event event) {
		return eventRepo.save(event);
	}
	
	public List<Event> getEvents() {
		return eventRepo.findAll();
	}
	public Event getEventById(Long id) {
		return eventRepo.findById(id).orElse(null);
	}
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
}
