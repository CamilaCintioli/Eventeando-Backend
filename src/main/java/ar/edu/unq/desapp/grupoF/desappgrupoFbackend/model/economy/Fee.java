package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidStatus;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PendingFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.UnpaidFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.InsufficientFundsException;

public class Fee {

    private final User user;
    public PaidStatus status;
    private double feeCharge;


    public Fee(User user) {
        this.user = user;
        this.status = new UnpaidFee();
        this.feeCharge = 200d;
    }

    public void charge() {
        try {
            user.chargeFee(this);
            this.status = new PaidFee();
        }
        catch (InsufficientFundsException e) {
            this.status = new PendingFee();
            throw e;
        }

    }

    public boolean isPaid(){
        return this.status.isPaid();
    }

    public double getFeeCharge() {
        return feeCharge;
    }
}
