package model.event;

import model.Item;
import model.User;
import model.exceptions.ItemAlreadyReservedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends Event{

    Map<Item, User> productsReserved = new HashMap<Item,User>();

    public List<Item> getProductsReserved() {
        return new ArrayList<Item>(productsReserved.keySet());
    }

    public List<Item> productsMissing() {
        List<Item> missing = new ArrayList<Item>(this.productsNeeded);
        this.productsReserved.forEach((k, v) -> {missing.remove(k);} );
        return missing;
    }

    public void reserve(Item item, User person) throws ItemAlreadyReservedException {
        if(!this.productsReserved.containsKey(item)) {
            this.productsReserved.put(item,person);
        } else {
            throw new ItemAlreadyReservedException(item,person);
        }
    }

    public Map<Item,User> reservas() { return this.productsReserved; }

}
