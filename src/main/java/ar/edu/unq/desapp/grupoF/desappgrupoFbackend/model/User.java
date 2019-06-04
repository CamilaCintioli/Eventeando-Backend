package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Fee;

import javax.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    //private LocalDateTime birthDate;
    //@OneToOne(targetEntity = Account.class, cascade=CascadeType.ALL)
    @Transient
    private Account account = new Account();
    @Column
    private boolean isDefaulter = false;

    public User() {}

    public User(String email, String name, String lastName, String password) {
        setEmail(email);
        setName(name);
        setPassword(password);
        setLastName(lastName);
        //setBirthDate(birthDate);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public LocalDateTime getBirthDate() {return birthDate;    }

    //public void setBirthDate(LocalDateTime birthDate) {this.birthDate = birthDate;}

    //MONEY TRANSACTIONS

    public void transferInto(double ammount, User sender) {
        this.account.transferInto(ammount,sender);
    }

    public void extract(double ammount){
        this.account.extract(ammount);
    }

    public void deposit(double ammount){
        this.account.deposit(ammount);
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public double getMoney() {
        return account.getBalance();
    }

    public void chargeFee(Fee fee) {
        this.account.chargeFee(fee,this);
    }

    public void markAsDefaulter() {
        this.isDefaulter=true;
    }

    public boolean isDefaulter() {
        return this.isDefaulter;
    }

    }