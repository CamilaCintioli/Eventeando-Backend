package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/event/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EventDTO add(@RequestBody EventDTO anEvent){
        System.out.println("EVENTO A CREAR");
        System.out.println("nombre: " + anEvent.getName() + " | descripcion: " + anEvent.getDescription() + " | dtype: " + anEvent.getEventType());
        eventService.saveEvent(anEvent);


        return anEvent;
    }

    @RequestMapping("/events")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @RequestMapping("/event/{eventId}")
    public ResponseEntity<EventDTO> get(@PathVariable Long eventId) {
        Optional<EventDTO> maybeEventDTO = eventService.getEvent(eventId);
        if (!maybeEventDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maybeEventDTO.get());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @DeleteMapping("/event/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        Long id = Long.parseLong(eventId);
        eventService.deleteEvent(id);
        return String.format("Eliminado evento de id: %d", id);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping(path="/event/edit/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> edit(@RequestBody EventDTO anEvent, @PathVariable Long eventId){
        Optional<EventDTO> maybeEventDTO = eventService.getEvent(eventId);
        if(!maybeEventDTO.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        anEvent.setId(eventId);
        EventDTO eventoResultate = new EventDTO(eventService.saveEvent(anEvent));
        return ResponseEntity.ok(eventoResultate);
    }
}
