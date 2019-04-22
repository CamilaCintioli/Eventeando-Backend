package model.validationStatus;

import model.event.Party;
import model.User;

import java.time.LocalDateTime;

public class ValidEventStatus implements ValidationStatus {

    public boolean isValid(Party party) {

        LocalDateTime now = LocalDateTime.now();
        boolean isValid = now.isBefore(party.getConfirmationDeadline());

        if (!isValid) this.nextStatus(party);
        return isValid;
    }

    public boolean isInvalid(Party party) {
        return !isValid(party);
    }

    public void acceptAttendee(User person, Party party) {
        party.addAttendee(person);
    }

    private void nextStatus(Party party) {
        party.setValidationStatus(new InvalidEventStatus());
    }

}


