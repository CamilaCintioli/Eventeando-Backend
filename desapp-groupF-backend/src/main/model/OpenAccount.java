package model;

public class OpenAccount {

    private Double balance;

    public OpenAccount() {
        this.balance = new Double(0);
    }

    public void pay(User person, Double amount) {
        this.balance += amount;
    }

    public Double getBalance() {
        return this.balance;
    }

    public Double extractAll(User person) {
        Double extraction = this.balance;
        this.balance = new Double(0);
        return extraction;
    }
}
