package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions;

public class EmailExistsException extends  RuntimeException {
    public EmailExistsException(String anException) {
        super(anException);
    }
}
