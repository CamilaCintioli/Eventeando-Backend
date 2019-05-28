package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;

public class ItemAlreadyReservedException extends Exception {
    public ItemAlreadyReservedException(Item item, User user) {
        super("El item "+item+"ya esta reservado por "+user);
    }
}
