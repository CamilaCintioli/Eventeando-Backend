package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;


import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.TransferenceDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/account/{emailUser}")
    public ResponseEntity recover(@PathVariable("emailUser") String emailUser ){

        return accountService.getAccountFrom(emailUser);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("account/new/transfer")
        public ResponseEntity transfer(@RequestBody TransferenceDTO transference ){

        return accountService.transfer(transference);

        }


}

