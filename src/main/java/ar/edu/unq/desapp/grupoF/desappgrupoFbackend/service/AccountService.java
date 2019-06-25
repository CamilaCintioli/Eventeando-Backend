package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.TransferenceDTO;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountFrom(String ownerEmail) {

       return accountRepository.findByEmailUser(ownerEmail);

    }

    public Account transfer(TransferenceDTO transference) {

        String emailSender = transference.getEmailSender();
        String emailReceiver = transference.getEmailReceiver();
        double amount = transference.getAmount();

        Account accountSender = accountRepository.findByEmailUser(emailSender);
        Account accountReceiver = accountRepository.findByEmailUser(emailReceiver);


        accountSender.transferTo(amount,accountReceiver);

        accountRepository.save(accountReceiver);
        accountRepository.save(accountSender);

        return accountSender;
    }


}
