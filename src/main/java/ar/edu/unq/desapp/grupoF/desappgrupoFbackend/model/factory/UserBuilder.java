package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.factory;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;

public class UserBuilder {

    private User anyUser;


    public UserBuilder anyUser() {
        User anUser = new User();
        anUser.setName("Pepita");
        anUser.setLastName("Obj");
        anUser.setEmail("pepita@gmail.com");
        anUser.setPassword("root");
        anyUser = anUser;
        return this;
    }

    public UserBuilder withName(String name) {
        anyUser.setName("Pepita");
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        anyUser.setLastName(lastName);
        return this;
    }

    public UserBuilder withEmail(String email) {
        anyUser.setEmail(email);
        if(email.equals("camila.cintioli@gmail.com")){
            this.setAccountCamila();
        } else {
            this.setAccountUriel();
        }

        return this;
    }


    public UserBuilder withPassword(String password) {
        anyUser.setPassword(password);
        return this;
    }

    public User get() {
        return anyUser;
    }

    private void setAccountCamila() {
        Account account = new Account(anyUser.getEmail());
        account.deposit(350d);
        account.extract(10d);
        anyUser.setAccount(account);
    }

    private void setAccount() {
        Account account = new Account(anyUser.getEmail());
        account.deposit(350d);
        account.extract(10d);
        anyUser.setAccount(account);
    }

    private void setAccountUriel() {
        Account account = new Account(anyUser.getEmail());
        account.deposit(200d);
        anyUser.setAccount(account);
    }

}

