package model.factory;

import model.event.Collect;
import model.event.Party;

import java.time.LocalDateTime;

public class EventFactory {


    public static Party anyParty() {

        Party party = new Party();
        party.setDeadlineConfirmation(LocalDateTime.now().plusHours(1));

        return party;
    }

    public static Collect anyCollect() {

        return new Collect();
    }
}
