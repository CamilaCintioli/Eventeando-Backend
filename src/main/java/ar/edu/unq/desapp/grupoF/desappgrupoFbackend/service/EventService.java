package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller.RequestPage;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Basket;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Collect;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.ItemAlreadyReservedException;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.EventRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
            EventDTO eventDTO = new EventDTO(e);
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
        Event event;

        if(!isNull(eventDTO.getId())){
            event = eventRepository.getEventById(eventDTO.getId()).get();
        } else {
            String eventType = eventDTO.getEventType();

            event = new Collect();

            if (eventType.equals("party")) {
                event = new Party();
                ((Party) event).setDeadlineConfirmation(eventDTO.getDeadlineConfirmation());

            }
            if (eventType.equals("basket")) {
                event = new Basket();
            }
        }

        event.setGuests(guests);
        event.setProductsNeeded(eventDTO.getProductsNeeded());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDayOfEvent(eventDTO.getDayOfEvent());

        if(isNull(eventDTO.getAttendeesCounter())){
            event.setAttendeesCounter(0l);
        } else {
            event.setAttendeesCounter(eventDTO.getAttendeesCounter());
        }


        return event;

    }

    private List<String> mailsFromEventGuests(Event event) {
        return event.getGuests().stream().map(user -> user.getEmail()).collect(Collectors.toList());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Optional<EventDTO> getEvent(Long id) {
        Optional<Event> maybeEvent = eventRepository.getEventById(id);
        if(maybeEvent.isPresent()){
            return Optional.of(new EventDTO(maybeEvent.get()));
        }
        return Optional.empty();
    }

    public EventDTO confirmAssistence(Long id, String email) {

        User user = userRepository.findByEmail(email);

        if(Objects.isNull(user)){
            throw new RuntimeException("There is no user with the given email");
        }

        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()){
            event.get().acceptAttendee(user);
            event.get().setAttendeesCounter(event.get().getAttendeesCounter()+1);
            eventRepository.save(event.get());
            return new EventDTO(event.get());
        } else{
            throw new RuntimeException("That event does not exist");
        }

    }

    public Page<EventDTO> getMostPopularEvents(String email, RequestPage requestPage) {

        return eventRepository.findAllByGuestsEmailOrderByAttendeeCounterDesc(email, PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);

    }

    public Page<EventDTO> getLastEvents(String email, RequestPage requestPage) {
        LocalDateTime date = LocalDateTime.now();
        return eventRepository.findAllByGuestsEmailAndDayOfEventLessThanOrderByDayOfEventDesc(email,date,PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);
    }

    public Page<EventDTO> getOngoingEvents(String email, RequestPage requestPage) {
        LocalDateTime date = LocalDateTime.now().withHour(00).withMinute(00).withSecond(00).withNano(000);

        return eventRepository.findAllByGuestsEmailAndDayOfEventGreaterThanEqualOrderByDayOfEventAsc(email,date,PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);
    }


}
