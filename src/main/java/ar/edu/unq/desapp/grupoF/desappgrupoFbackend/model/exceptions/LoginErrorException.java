package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions;

public class LoginErrorException extends  RuntimeException {
    public LoginErrorException(String errorMessage) {
        super(errorMessage);
    }
}