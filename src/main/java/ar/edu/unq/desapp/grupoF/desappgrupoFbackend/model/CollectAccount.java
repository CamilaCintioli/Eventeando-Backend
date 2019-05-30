package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectAccount {

    @Id
    @GeneratedValue
    Long id;
    @Column
    private Double balance;

    public CollectAccount() {
        this.balance = 0d;
    }

    public void pay(User person, Double amount) {
        this.balance += amount;
    }

    public Double getBalance() {
        return this.balance;
    }

    public Double extractAll(User person) {
        Double extraction = this.balance;
        this.balance = 0d;
        return extraction;
    }
}
