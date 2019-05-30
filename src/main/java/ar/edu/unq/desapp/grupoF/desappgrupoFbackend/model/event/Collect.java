package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.CollectAccount;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.CollectionHasntReachedException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Collect extends Event{

    @OneToOne(cascade = CascadeType.ALL, targetEntity = CollectAccount.class, fetch = FetchType.LAZY)
    CollectAccount account = new CollectAccount();

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

