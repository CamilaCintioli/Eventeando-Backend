package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.event;

public class EventDTO {
    private final Event event;

    public EventDTO(Event event) {

        this.event= event;
    }

    public String getName(){
        return this.event.getName();
    }
}
