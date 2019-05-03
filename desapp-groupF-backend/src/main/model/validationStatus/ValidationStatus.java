package model.validationStatus;

import model.event.Party;
import model.User;

public interface ValidationStatus {

    boolean isValid(Party party);

    void acceptAttendee(User person, Party party);
}
