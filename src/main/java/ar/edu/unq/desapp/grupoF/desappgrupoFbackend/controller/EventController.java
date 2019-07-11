package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.pagination.RequestPage;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("EVENTO A CREAR");
        System.out.println(anEvent);
        Event eventoCreado = eventService.saveEvent(anEvent);

        return new EventDTO(eventoCreado);
        //return anEvent;
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
    public ResponseEntity<String> deleteEvent(@PathVariable String eventId) {

        return eventService.deleteEvent(eventId);

    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping(path="/event/edit/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity edit(@RequestBody EventDTO anEvent, @PathVariable Long eventId){
        Optional<EventDTO> maybeEventDTO = eventService.getEvent(eventId);
        if(!maybeEventDTO.isPresent()){
            return ResponseEntity.badRequest().body("There is no event with given id");
        }
        anEvent.setId(eventId);
        EventDTO editedEvent = new EventDTO(eventService.saveEvent(anEvent));
        return ResponseEntity.ok(editedEvent);
    }

    @PostMapping(path="/event/{eventId}/assist/{email}")
    public ResponseEntity confirmAssitence(@PathVariable("eventId") String eventId, @PathVariable("email") String email){

        return eventService.confirmAssistence(eventId, email);
    }

    @GetMapping(path = "/event/popular/{email}/{index}/{size}")
    public ResponseEntity getMostPopularEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();

        try{
            page.setIndex(index);
            page.setSize(size);
        }
        catch (Exception e){
            ResponseEntity.badRequest().body(e.getMessage());
        }

        return eventService.getMostPopularEvents(email, page);
    }

    @GetMapping(path="/event/last/{email}/{index}/{size}")
    public ResponseEntity getLastEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();
        try{
            page.setIndex(index);
            page.setSize(size);
        }
        catch (Exception e){
            ResponseEntity.badRequest().body("The index ans size of the page should be a number");
        }

        return eventService.getLastEvents(email,page);
    }

    @GetMapping(path="/event/ongoing/{email}/{index}/{size}")
    public ResponseEntity getOngoingEvents(@PathVariable("email") String email, @PathVariable("index") int index, @PathVariable("size") int size){

        RequestPage page = new RequestPage();
        try{
            page.setIndex(index);
            page.setSize(size);
        }
        catch (Exception e){
            ResponseEntity.badRequest().body("The index ans size of the page should be a number");
        }

        return eventService.getOngoingEvents(email,page);
    }

    @PutMapping(path="/event/reserve/{eventId}/{productName}/{emailUser}")
    public ResponseEntity reserveProductInEvent(@PathVariable("eventId") String eventId, @PathVariable("productName") String productName, @PathVariable("emailUser") String emailUser){

        return eventService.reserveProduct(eventId,productName,emailUser);
    }

}
