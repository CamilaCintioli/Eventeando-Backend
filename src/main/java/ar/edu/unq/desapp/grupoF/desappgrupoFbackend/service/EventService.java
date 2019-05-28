package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event){
        eventRepository.save(event);
        return event;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
