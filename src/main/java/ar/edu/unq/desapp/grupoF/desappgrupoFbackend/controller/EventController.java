package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/event/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EventDTO add(@RequestBody EventDTO anEvent){

        eventService.saveEvent(anEvent);


        return anEvent;
    }

    @RequestMapping("/events")
    public List<EventDTO> getAllEvents() {

        return eventService.getAllEvents();



    }
}
