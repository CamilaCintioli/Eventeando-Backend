package model.economy;

import model.User;
import model.economy.feeStatus.PaidFee;
import model.economy.feeStatus.PaidStatus;
import model.economy.feeStatus.PendingFee;
import model.economy.feeStatus.UnpaidFee;
import model.exceptions.InsufficientFundsException;

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
