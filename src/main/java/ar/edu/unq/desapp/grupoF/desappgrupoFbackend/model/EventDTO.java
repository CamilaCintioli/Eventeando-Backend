package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;

public class EventDTO {
    private final Event event;

    public EventDTO(Event event) {

        this.event= event;
    }

    public String getType(){
        return this.event.getClass().getSimpleName();
    }
}
