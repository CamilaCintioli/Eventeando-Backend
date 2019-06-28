package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Loan;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.AccountRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity giveLoan(String email){

        Account account = accountRepository.findByEmailUser(email);

        if(account==null) return ResponseEntity.badRequest().body("The given mail does not have an account");

        if (!isEligibleForLoan(email)) return ResponseEntity.badRequest().body("The user is not elegible for a loan");

        Loan loan = new Loan(email, account);

        loanRepository.save(loan);

        return ResponseEntity.ok(loan);
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

    public ResponseEntity getLoan(String email) {

        Optional<Loan> loan = Optional.ofNullable(loanRepository.findByUserEmail(email));

        if(! loan.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(loan.get());

        

    }
}
