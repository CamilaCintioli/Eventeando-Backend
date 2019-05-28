package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.validationStatus;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;

public interface ValidationStatus {

    boolean isValid(Party party);

    void acceptAttendee(User person, Party party);
}
