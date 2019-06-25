package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions;

public class ThereIsNoLoanException extends RuntimeException {
    public ThereIsNoLoanException(String anException) {
        super(anException);
    }
}
