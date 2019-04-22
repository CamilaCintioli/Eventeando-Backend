package model.exceptions;

import model.Item;
import model.User;

public class ItemAlreadyReservedException extends Exception {
    public ItemAlreadyReservedException(Item item, User user) {
        super("El item "+item+"ya esta reservado por "+user);
    }
}
