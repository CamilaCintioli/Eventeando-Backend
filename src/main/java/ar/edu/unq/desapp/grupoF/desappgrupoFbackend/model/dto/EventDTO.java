package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventDTO {

    Long id;
    String name;
    String description;
    List<Item> productsNeeded = new ArrayList<Item>();
    List<String> guestsMails;
    String eventType;
    List<String> attendees;
    LocalDateTime deadlineConfirmation;
    Long attendeesCounter;


    public EventDTO(){}

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.productsNeeded = event.getProductsNeeded();
        this.guestsMails = event.getGuests().stream().map(user -> user.getEmail()).collect(Collectors.toList());
        this.eventType = event.getClass().getSimpleName();
        this.attendees = event.getAttendees().stream().map(user -> user.getEmail()).collect(Collectors.toList());
        if(this.eventType.equals("Party")){
            this.deadlineConfirmation = ((Party) event).getConfirmationDeadline();
        }
        this.attendeesCounter = event.getAttendeesCounter();

    }

    public void setProductsNeeded(List<Item> productsNeeded) {
        this.productsNeeded = productsNeeded;
    }


    public List<String> getGuestsMails() {
        return guestsMails;
    }

    public void setGuestsMails(List<String> guestsMails) {
        this.guestsMails = guestsMails;
    }

    public List<Item> getProductsNeeded() {
        return productsNeeded;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType){
        this.eventType = eventType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttendees(List<String> emailsAttendees){
        this.attendees = emailsAttendees;
    }

    public List<String> getAttendees(){
        return this.attendees;
    }

    public LocalDateTime getDeadlineConfirmation() {
        return this.deadlineConfirmation;
    }

    public void setDeadlineConfirmation(String date){
        //2019-06-27

        String dateTime = date + " 00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);

        this.deadlineConfirmation= formatDateTime;

    }

    public void setAttendeesCounter(Long attendeesCounter){
        this.attendeesCounter = attendeesCounter;
    }

    public Long getAttendeesCounter(){
        return this.attendeesCounter;
    }

}
