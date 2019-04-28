package model;

import model.economy.Account;

import java.time.LocalDateTime;

public class User {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime birthDate;
    private Account account = new Account();

    public User() {}

    public User(String email, String name, String lastName, String password, LocalDateTime birthDate) {
        setEmail(email);
        setName(name);
        setPassword(password);
        setLastName(lastName);
        setBirthDate(birthDate);
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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void transferInto(double ammount, User sender) {
        this.account.transferInto(ammount,sender);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}