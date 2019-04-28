package model.economy;

import model.User;
import model.exceptions.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;


public class Account {

    private double balance = 0;
    private List<Movement> history = new ArrayList<Movement>();

    public void setBalance(double ammount) {
        this.balance=ammount;
    }

    public double getBalance() {
        return this.balance;
    }

    //RAPIPAGO.
    public void deposit(double ammount){

        this.history.add(new Movement(ammount, MovementType.DEPOSIT));

        this.balance +=  ammount;
    }

    public void extract(double ammount) {

        if(ammount>this.balance) throw new InsufficientFundsException(ammount,this.balance);

        this.history.add(new Movement(ammount,MovementType.EXTRACTION));

        this.balance -= ammount;

    }

    //TRANSFERENCIAS CUENTA A CUENTA.
    public void transferInto(double ammount, User sender) {

        this.history.add(new Movement(ammount,MovementType.TRANSFERINTO,sender));
        this.balance += ammount;
    }
    
    public void transferTo(double ammount, User reciever, User sender){

        if(ammount>this.balance) throw new InsufficientFundsException(ammount,this.balance);

        this.balance -= ammount;

        this.history.add(new Movement(ammount,MovementType.TRANSFERTO,reciever));

        reciever.transferInto(ammount,sender);
    }

    public List<Movement> getHistory() {
        return this.history;
    }

}
