package model.ValidationStatus;

import model.Party;

import java.time.LocalDateTime;

public class ValidEventStatus implements ValidationStatus {

    public boolean isValid(Party party) {
        LocalDateTime now = LocalDateTime.now();
        return  now.isBefore(party.getConfirmationDeadline());
    }

}


