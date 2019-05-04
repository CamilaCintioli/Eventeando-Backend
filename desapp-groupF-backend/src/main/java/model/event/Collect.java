package model.event;

import model.OpenAccount;
import model.User;
import model.exceptions.CollectionHasntReachedException;

public class Collect extends Event{
    OpenAccount account = new OpenAccount();

    public Double totalCost() { return productsNeeded.stream().mapToDouble(product -> product.totalPrice()).sum(); }

    public Double chargePerAttendee() {
        if (attendees.size() > 0) {
            return this.totalCost() / attendees.size();
        } else {
            return new Double(0);
        }
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

