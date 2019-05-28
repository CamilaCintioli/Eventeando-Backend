package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue
    Long id;

    public Event(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

}
