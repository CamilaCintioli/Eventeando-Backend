package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller.RequestPage;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.EventDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Basket;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Collect;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.EventRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
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

        Event event = this.createEvent(eventDTO);

        eventRepository.save(event);

        return event;
    }

    public List<EventDTO> getAllEvents() {

        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventDTO::new).collect(Collectors.toList());

    }

    private List<User> getEventGuests(List<String> guestsMails) {

        this.validateEmails(guestsMails);

        return guestsMails.stream().map(mail -> userRepository.findByEmail(mail)).filter(user -> user!=null).collect(Collectors.toList());


    }

    private Event createEvent(EventDTO eventDTO) {

        List<User> guests = this.getEventGuests(eventDTO.getGuestsMails());

        Event event;

        if(!isNull(eventDTO.getId())){
            event = eventRepository.getEventById(eventDTO.getId()).get();
        } else {
            String eventType = eventDTO.getEventType();

            event = new Collect();

            if (eventType.equals("Party")) {
                event = new Party();
                ((Party) event).setDeadlineConfirmation(eventDTO.getDeadlineConfirmation());

            }
            if (eventType.equals("Basket")) {
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

    public ResponseEntity<String> deleteEvent(String eventId) {

        Long id;

        try {
            id = Long.parseLong(eventId);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("The event id should be a number");
        }

        try{
            eventRepository.deleteById(id);
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("There is no event with the given id: " + id);
        }

        return ResponseEntity.ok("The event"+ eventId +"was successfully deleted");
    }

    public Optional<EventDTO> getEvent(Long id) {
        Optional<Event> maybeEvent = eventRepository.getEventById(id);
        if(maybeEvent.isPresent()){
            return Optional.of(new EventDTO(maybeEvent.get()));
        }
        return Optional.empty();
    }

    public ResponseEntity confirmAssistence(String eventId, String email) {

        Long id;

        try{
            id = Long.parseLong(eventId);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("The event id should be a number");
        }

        User user = userRepository.findByEmail(email);

        if(user==null){
            return ResponseEntity.badRequest().body("There is no user with given email");
        }

        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()){
            try{
                event.get().acceptAttendee(user);
            }
            catch(Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }

            event.get().setAttendeesCounter(event.get().getAttendeesCounter()+1);
            eventRepository.save(event.get());
            return ResponseEntity.ok(new EventDTO(event.get()));
        } else{
            return ResponseEntity.badRequest().body("That event does not exist");
        }

    }

    public ResponseEntity getMostPopularEvents(String email, RequestPage requestPage) {

        Page<EventDTO> events = eventRepository.findAllByGuestsEmailOrderByAttendeeCounterDesc(email, PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);

        return ResponseEntity.ok(events);
    }

    public ResponseEntity getLastEvents(String email, RequestPage requestPage) {
        LocalDateTime date = LocalDateTime.now();
        Page<EventDTO> events = eventRepository.findAllByGuestsEmailAndDayOfEventLessThanOrderByDayOfEventDesc(email,date,PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);

        return ResponseEntity.ok(events);
    }

    public ResponseEntity getOngoingEvents(String email, RequestPage requestPage) {
        LocalDateTime date = LocalDateTime.now().withHour(00).withMinute(00).withSecond(00).withNano(000);

        Page<EventDTO> events = eventRepository.findAllByGuestsEmailAndDayOfEventGreaterThanEqualOrderByDayOfEventAsc(email,date,PageRequest.of(requestPage.getIndex(),requestPage.getSize()))
                .map(EventDTO::new);

        return ResponseEntity.ok(events);
    }

    public ResponseEntity reserveProduct(String  eventId, String productName, String emailUser) {

        Long id;

        try{
            id = Long.parseLong(eventId);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("The event id given should be a number");
        }


        Optional<Event> eventOptional = eventRepository.getEventById(id);
        User user = userRepository.findByEmail(emailUser);
        EventDTO eventDTO = new EventDTO();

        if(eventOptional.isPresent() && !Objects.isNull(user)){

            Basket basket = ((Basket) eventOptional.get());


            try {
                Item item = this.findItemInBasket(basket,productName);
                basket.reserve(item,user);
                eventRepository.save(basket);
                eventDTO = new EventDTO(basket);

            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        } else {
           return ResponseEntity.badRequest().body("There is no event and/or with given id");
        }

        return ResponseEntity.ok(eventDTO);
    }

    private Item findItemInBasket(Basket basket, String productName) {

        Optional<Item> optionalItem = basket.getProductsNeeded().stream().filter(item -> item.getProduct().getName().equals(productName)).findFirst();

        if(!optionalItem.isPresent()){
            throw new RuntimeException("There is no item with the given name in this event");
        }

        return optionalItem.get();
    }

    private void validateEmails(List<String> emails) {

        if(emails.stream().filter(email -> this.checkEmail(email)).count() != emails.size()) {
            throw new RuntimeException("The mails are not valid");
        }
    }

    private boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
