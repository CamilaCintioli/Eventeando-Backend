package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Loan;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.ThereIsNoLoanException;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.UserIsNotEligibleToReceiveALoanException;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.AccountRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, AccountRepository accountRepository) {
        this.loanRepository = loanRepository;
        this.accountRepository = accountRepository;
    }

    public Loan giveLoan(String email){

        //TODO: tirar error si no encuentra el email.

        Account account = accountRepository.findByEmailUser(email);

        if (!isEligibleForLoan(email)) throw new UserIsNotEligibleToReceiveALoanException();
        Loan loan = new Loan(email, account);

        loanRepository.save(loan);

        return loan;
    }

    private boolean isEligibleForLoan(String email) {
        return isCumplidor(email) && !hasCurrentLoan(email);
    }

    private boolean hasCurrentLoan(String userEmail) {
        //TODO
        //query a DAOLoan.
        return false;
    }

    private boolean isCumplidor(String userEmail) {
        //TODO
        //query a DAOEventos.
        return true;
    }

    public void chargeFees(String email) {
        //query a DAOLoan p/ conseguir todas las cuotas.
        Account account = accountRepository.findByEmailUser(email);
        List<Loan> loans = new ArrayList<>();
        for(Loan loan: loans){
            loan.getCurrentFee().charge(account);
        }
    }

    public Loan getLoan(String email) {

        Optional<Loan> loan = Optional.ofNullable(loanRepository.findByUserEmail(email));

        if(! loan.isPresent()){
            throw new ThereIsNoLoanException("There is not loan for this user" + email);
        }
        return loan.get();

        

    }
}
