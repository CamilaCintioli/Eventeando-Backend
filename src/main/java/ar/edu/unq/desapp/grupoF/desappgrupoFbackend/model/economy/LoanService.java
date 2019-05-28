package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.UserIsNotEligibleToReceiveALoanException;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

    public void giveLoan(User user){

        if (!isEligibleForLoan(user)) throw new UserIsNotEligibleToReceiveALoanException();
        Loan loan = new Loan(user);
        //persistir loan.
    }

    private boolean isEligibleForLoan(User user) {
        return isCumplidor(user) && !hasCurrentLoan(user);
    }

    private boolean hasCurrentLoan(User user) {
        //query a DAOLoan.
        return false;
    }

    private boolean isCumplidor(User user) {
        //query a DAOEventos.
        return true;
    }

    public void chargeFees() {
        //query a DAOLoan p/ conseguir todas las cuotas.
        List<Loan> loans = new ArrayList<>();
        for(Loan loan: loans){
            loan.getCurrentFee().charge();
        }
    }
}
