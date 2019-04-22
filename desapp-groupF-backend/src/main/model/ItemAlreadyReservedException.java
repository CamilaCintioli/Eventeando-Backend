package model;

public class ItemAlreadyReservedException extends Exception {
    public ItemAlreadyReservedException(Item item, User user) {
        super("El item "+item+"ya esta reservado por "+user);
    }
}
