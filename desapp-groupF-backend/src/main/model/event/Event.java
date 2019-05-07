package model.event;

import model.Item;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class Event {

    List<User> attendees = new ArrayList<User>();
    List<Item> productsNeeded = new ArrayList<Item>();
    List<User> guests = new ArrayList<>();


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
    }


}

