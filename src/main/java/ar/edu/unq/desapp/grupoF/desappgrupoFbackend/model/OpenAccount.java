package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

public class OpenAccount {

    private Double balance;

    public OpenAccount() {
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
