package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDTO {

    List<Item> productsNeeded = new ArrayList<Item>();
    List<String> guestsMails;
    String eventType;

    public EventDTO(){}

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

}
