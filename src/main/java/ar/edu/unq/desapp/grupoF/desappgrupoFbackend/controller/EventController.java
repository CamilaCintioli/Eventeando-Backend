package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping(path="/event/{eventId}/assist/{email}")
    public EventDTO confirmAssitence(@PathVariable("eventId") String eventId, @PathVariable("email") String email){
        Long id = Long.parseLong(eventId);
        return eventService.confirmAssistence(id, email);
    }

    @GetMapping(path = "/event/popular/{email}/{index}/{size}")
    public Page<EventDTO> getMostPopularEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();
        page.setIndex(index);
        page.setSize(size);

        return eventService.getMostPopularEvents(email, page);
    }

    @GetMapping(path="/event/last/{email}/{index}/{size}")
    public Page<EventDTO> getLastEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();
        page.setIndex(index);
        page.setSize(size);

        return eventService.getLastEvents(email,page);
    }

    @GetMapping(path="/event/ongoing/{email}/{index}/{size}")
    public Page<EventDTO> getOngoingEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();
        page.setIndex(index);
        page.setSize(size);

        return eventService.getOngoingEvents(email,page);
    }



}
