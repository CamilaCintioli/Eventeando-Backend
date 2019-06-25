package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidStatus;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PendingFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.UnpaidFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.InsufficientFundsException;

import javax.persistence.*;

@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Column
    private String emailUser;
    @Convert(converter = PaidStatusConverter.class)
    public PaidStatus status;
    @Column
    private double feeCharge;

    public Fee(){}

    public Fee(String emailUser) {
        this.emailUser = emailUser;
        this.status = new UnpaidFee();
        this.feeCharge = 200d;
    }

    public void charge(Account account) {
        try {
            account.chargeFee(this);
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
