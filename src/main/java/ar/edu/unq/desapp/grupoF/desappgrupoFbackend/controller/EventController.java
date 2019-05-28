package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.event.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PutMapping("/new/event")
    public Event add(){
        Event anEvent = new Event();
        anEvent.setName("Pepita");
        Event anEvent2 = new Event();
        anEvent.setName("Pepon");

        eventService.saveEvent(anEvent2);
        return eventService.saveEvent(anEvent);
    }

    @RequestMapping("/events")
    public List<EventDTO> getAllEvents() {


        List<Event> events = eventService.getAllEvents();
        List<EventDTO> eventDtos= new ArrayList<>();

        for(Event e: events){
            eventDtos.add(new EventDTO(e));
        }

        return eventDtos;

    }
}
