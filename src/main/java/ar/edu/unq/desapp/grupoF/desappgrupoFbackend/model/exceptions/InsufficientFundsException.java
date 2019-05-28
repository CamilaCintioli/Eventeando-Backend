package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double ammount, double balance) {
        super("Tenes " + balance + " pesos, no podes extraer: " + ammount);
    }
}
