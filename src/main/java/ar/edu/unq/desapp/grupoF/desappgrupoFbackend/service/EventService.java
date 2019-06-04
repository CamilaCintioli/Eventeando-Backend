package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Basket;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Collect;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.EventRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.PartyRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository=userRepository;
    }


    public Event saveEvent(EventDTO eventDTO){

        List<User> guests = this.getEventGuests(eventDTO.getGuestsMails());

        Event event = this.createEvent(eventDTO, guests);

        eventRepository.save(event);

        return event;
    }


    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventsDTO = new ArrayList<>();

        for (Event e : events) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setGuestsMails(this.mailsFromEventGuests(e));
            eventDTO.setProductsNeeded(e.getProductsNeeded());
            eventDTO.setEventType(e.getClass().getSimpleName());
            eventsDTO.add(eventDTO);
        }

        return  eventsDTO;
    }

    private List<User> getEventGuests(List<String> guestsMails) {

        List<User> guests = new ArrayList<>();

        for(String mail: guestsMails){
            guests.add(userRepository.findByEmail(mail));
        }

        return guests;

    }

    private Event createEvent(EventDTO eventDTO, List<User> guests) {

        String eventType = eventDTO.getEventType();
        Event event = new Collect();


        if(eventType.equals("party")){
            event = new Party();


        }
        if(eventType.equals("basket")){
            event = new Basket();
        }

        System.out.println("----------------"+eventType+"------------------------------------------------------");

        System.out.println("----------------"+guests+"------------------------------------------------------");
        event.setGuests(guests);
        event.addProductsNeeded(eventDTO.getProductsNeeded());

        return event;

    }

    private List<String> mailsFromEventGuests(Event event) {
        return event.getGuests().stream().map(user -> user.getEmail()).collect(Collectors.toList());
    }
}
