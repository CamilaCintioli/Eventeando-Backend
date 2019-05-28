package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.factory;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Basket;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Collect;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;

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

    public static Basket anyBasket() {
        return new Basket();
    }
}
