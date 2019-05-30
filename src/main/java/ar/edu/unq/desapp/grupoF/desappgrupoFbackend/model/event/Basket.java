package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.exceptions.ItemAlreadyReservedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Basket extends Event{

    @ElementCollection
    @CollectionTable(name="BASKET_RESERVED_PRODUCTS")
    @Column(name="USER_ID")
    @MapKeyJoinColumn(name="ITEM_ID")
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
