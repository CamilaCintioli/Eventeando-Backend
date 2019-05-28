package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.validationStatus;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.DeadlineToConfirmAttendanceHasPassed;


public class InvalidEventStatus implements ValidationStatus {

    public boolean isValid(Party party) {
        return false;
    }

    public void acceptAttendee(User person, Party party) {
        throw new DeadlineToConfirmAttendanceHasPassed("Deadline to confirm attendance has already passed.");
    }
}


