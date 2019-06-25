package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;


import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.TransferenceDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Loan;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/loan/new/{email}")
    public Loan createLoan(@PathVariable("email") String email){

        return loanService.giveLoan(email);

    }



}

