package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.mailing.EmailSender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Transient
    List<User> attendees = new ArrayList<User>();
    @Transient
    List<Item> productsNeeded = new ArrayList<Item>();
    @Transient
    List<User> guests = new ArrayList<>();
    @Transient
    EmailSender mailSender = new EmailSender();

    public Event(){}

    public void acceptAttendee(User person) {
        this.attendees.add(person);
    }

    public boolean isAttending(User person) {
        return this.attendees.contains(person);
    }

    public void addProductsNeeded(List<Item> productsNeeded) {
        this.productsNeeded.addAll(productsNeeded);
    }

    public List<Item> getProductsNeeded() { return this.productsNeeded; }

    public void setGuests(List<User> guests)  {
        this.guests = guests;

        List<String> guestsMails = guests.stream().map(g -> g.getEmail()).collect(Collectors.toList());
        String message = "Veni al evento con nosotros.";

        mailSender.sendEmail(guestsMails,"Invitacion", message);

    }


    public List<User> getGuests() {
        return this.guests;
    }
}

