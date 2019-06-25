package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;

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

    public EventDTO(){}

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.productsNeeded = event.getProductsNeeded();
        this.guestsMails = event.getGuests().stream().map(user -> user.getEmail()).collect(Collectors.toList());
        this.eventType = event.getClass().getSimpleName();
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

}
