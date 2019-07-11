package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.mailing.EmailSender;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Event {



    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    String description;
    @ManyToMany(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
    List<User> attendees = new ArrayList<User>();
    @OneToMany(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
    List<Item> productsNeeded = new ArrayList<Item>();
    @ElementCollection
    List<String> guests = new ArrayList<>();
    Long attendeeCounter;
    LocalDateTime dayOfEvent;
    String creatorEmail;
    @Transient
    EmailSender mailSender = new EmailSender();

    public Event(){}

    public void acceptAttendee(User person) {
        if(this.guests.contains(person)){
            this.attendees.add(person);
        }
        else {
            throw new RuntimeException("The user can assist an event if is not invited");
        }
    }

    public boolean isAttending(User person) {
        return this.attendees.contains(person);
    }

    public void setProductsNeeded(List<Item> productsNeeded) {
        this.productsNeeded = productsNeeded;
    }

    public void addProductsNeeded(List<Item> productsNeeded) {
        this.productsNeeded.addAll(productsNeeded);
    }

    public List<Item> getProductsNeeded() { return this.productsNeeded; }

    public void setGuests(List<String> guests)  {
        this.guests = guests;

    }


    public List<String> getGuests() {
        return this.guests;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getAttendees() {
        return this.attendees;
    }


    public void setAttendeesCounter(Long attendeesCounter) {
        this.attendeeCounter = attendeesCounter;
    }

    public Long getAttendeesCounter() {
        return this.attendeeCounter;
    }

    public void setDayOfEvent(LocalDateTime date){
        this.dayOfEvent = date;
    }

    public LocalDateTime getDayOfEvent(){
        return  this.dayOfEvent;
    }

    public String getCreatorEmail(){
        return this.creatorEmail;
    }

    public void setCreatorEmail(String email){
        this.creatorEmail=email;
    }
}

