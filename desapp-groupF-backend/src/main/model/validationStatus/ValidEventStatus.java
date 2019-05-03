package model.validationStatus;

import model.event.Party;
import model.User;

import java.time.LocalDateTime;

public class ValidEventStatus implements ValidationStatus {

    public void acceptAttendee(User person, Party party) {

        if(!isValid(party))this.nextStatus(party, person);
        party.addAttendee(person);

    }

    public boolean isValid(Party party) {

        LocalDateTime confirmationDeadline = party.getConfirmationDeadline();
        LocalDateTime now = LocalDateTime.now();
        boolean isValid = now.isBefore(confirmationDeadline);

        return isValid;
    }

    private void nextStatus(Party party, User person) {
        party.setValidationStatus(new InvalidEventStatus());
        party.acceptAttendee(person);
    }

}


