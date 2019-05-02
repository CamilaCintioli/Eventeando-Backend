package model.economy.feeStatus;

public class PaidFee extends PaidStatus {

    @Override
    public boolean isPaid(){
        return true;
    }
}
