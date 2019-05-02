package model.economy;

import model.User;

import java.util.Arrays;
import java.util.List;

public class Loan {

    User user;
    private Fee currentFee;
    private List<Fee> fees;

    public Loan(User user) {
        this.user = user;
        this.fees = Arrays.asList(new Fee(user), new Fee(user), new Fee(user), new Fee(user), new Fee(user), new Fee(user));

        user.transferInto(1000d,null);

    }

    public Fee getCurrentFee() {
        for(Fee fee:fees){

            if(!fee.isPaid()) return fee;
        }

        return null;
    }

    public List<Fee> getFees() {
        return this.fees;
    }
}
