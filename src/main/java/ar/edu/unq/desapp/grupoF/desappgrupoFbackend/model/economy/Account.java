package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.InsufficientFundsException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Column
    private double balance = 0;
    @Column
    private String emailUser;
    @OneToMany(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private List<Movement> history = new ArrayList<Movement>();
    @Transient
    private Fee pendingFee = null;

    public Account(){}

    public Account(String email) {
        this.emailUser= email;
    }

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

        this.chargePendingFeeIfPossible();

    }

    private void chargePendingFeeIfPossible() {

        if(pendingFee!=null && balance>= pendingFee.getFeeCharge()) {
            this.chargeFee(pendingFee);
            pendingFee = null;
        }
    }

    public void extract(double ammount) {

        if(ammount>this.balance) throw new InsufficientFundsException(ammount,this.balance);

        this.history.add(new Movement(ammount,MovementType.EXTRACTION));

        this.balance -= ammount;

    }

    //TRANSFERENCIAS CUENTA A CUENTA.
    public void transferInto(double ammount, String emailSender) {

        this.history.add(new Movement(ammount,MovementType.TRANSFERINTO,emailSender));
        this.balance += ammount;
        this.chargePendingFeeIfPossible();
    }

    public void transferTo(double ammount, Account receiver){

        if(ammount>this.balance) throw new InsufficientFundsException(ammount,this.balance);

        this.balance -= ammount;

        this.history.add(new Movement(ammount,MovementType.TRANSFERTO,receiver.getEmailUser()));

        receiver.transferInto(ammount,this.getEmailUser());
    }

    public String getEmailUser() {

        return this.emailUser;

    }

    public List<Movement> getHistory() {
        return this.history;
    }

    //LOANS.

    public void chargeFee(Fee fee, User user) {

        double feeCharge = fee.getFeeCharge();

        if(feeCharge>this.balance) {
            this.pendingFee = fee;
            user.markAsDefaulter();
            throw new InsufficientFundsException(feeCharge,this.balance);
        }

        this.history.add(new Movement(feeCharge,MovementType.EXTRACTION));

        this.balance -= feeCharge;

    }

    public void setEmailUser(String email) {
        this.emailUser=email;
    }

    public void chargeFee(Fee fee) {

        double feeCharge = fee.getFeeCharge();

        if(feeCharge>this.balance) {
            this.pendingFee = fee;
            //.markAsDefaulter();
            throw new InsufficientFundsException(feeCharge,this.balance);
        }

        this.history.add(new Movement(feeCharge,MovementType.EXTRACTION));

        this.balance -= feeCharge;

    }
}
