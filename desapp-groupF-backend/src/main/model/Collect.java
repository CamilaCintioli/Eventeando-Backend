package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Collect {
    List<User> attendees= new ArrayList<User>();
    List<Item> productsNeeded = new ArrayList<Item>();
    OpenAccount account = new OpenAccount();

    /*public Collect(OpenAccount account) {
        this.account = account;
    }*/

    public void acceptAttendee(User person) { this.attendees.add(person); }

    public void addProductsNeeded(List<Item> productsNeeded) { this.productsNeeded.addAll(productsNeeded); }

    public Double totalCost() { return productsNeeded.stream().mapToDouble(product -> product.price()).sum(); }

    public Double chargePerAttendee() {
        if (attendees.size() > 0) {
            return this.totalCost() / attendees.size();
        } else {
            return new Double(0);
        }
    }

    public boolean isAttending(User person)  {
        return this.attendees.contains(person);
    }

    public void pay(User person, Double amount) {
        this.account.pay(person, amount);
    }

    public Double pendingCost() {
        return this.totalCost() - this.account.getBalance();
    }

    private Boolean collectionReached() {
        return pendingCost().equals(new Double(0));
    }

    public Double extractAll(User person) throws CollectionHasntReachedException {
        if (this.collectionReached()){
            return this.account.extractAll(person);
        }
        throw new CollectionHasntReachedException(this.totalCost(), this.pendingCost());
    }
}

