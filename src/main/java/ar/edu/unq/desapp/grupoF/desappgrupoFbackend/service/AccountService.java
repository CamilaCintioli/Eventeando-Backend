package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.TransferenceDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity getAccountFrom(String ownerEmail) {

       Account account =accountRepository.findByEmailUser(ownerEmail);

       if(account==null){
           return ResponseEntity.badRequest().body("There is no account for given email");
       }

       return ResponseEntity.ok(account);

    }

    public ResponseEntity transfer(TransferenceDTO transference) {

        String emailSender = transference.getEmailSender();
        String emailReceiver = transference.getEmailReceiver();
        Double amount = transference.getAmount();

        if(amount==null){
            return ResponseEntity.badRequest().body("There must be an amount to transfer");
        }

        Account accountSender = accountRepository.findByEmailUser(emailSender);
        Account accountReceiver = accountRepository.findByEmailUser(emailReceiver);

        if(accountReceiver==null||accountReceiver==null){
            return ResponseEntity.badRequest().body("There is no account with given email");
        }

        try {
            accountSender.transferTo(amount,accountReceiver);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        accountRepository.save(accountReceiver);
        accountRepository.save(accountSender);

        return ResponseEntity.ok(accountSender);
    }


}
