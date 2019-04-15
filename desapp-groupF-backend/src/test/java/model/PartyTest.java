package model;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartyTest {

    @Test
    public void aPartyCanAcceptAnAttendee(){

        Party party = new Party();
        User person = new User();
        party.acceptAttendee(person);

        assertTrue(party.isAttending(person));
    }

    @Test
    public void aPartyHasProducts(){

        Party party = new Party();

        List<Pair<Product,Integer>> productsNeeded = new ArrayList<>();

        Product coke = new Product("Coke",20);
        Pair<Product,Integer> productQuantity = new Pair<>(coke,20);

        productsNeeded.add(productQuantity);

        party.addProductsNeeded(productsNeeded);

        assertEquals(1,party.getProductsNeeded().size());
        assertTrue(party.getProductsNeeded().contains(productQuantity));

    }

    @Test
    public void aPartyCalculatesShoppingListWithNoAttendeesAndHasNothingToBuy(){

        Party party = new Party();
        setProductsForEvent(party);

        List<Pair<Product, Integer>> shoppingList = party.calculateShoppingList();

        assertTrue(party.hasNothingToBuy(shoppingList));
    }

    private void setProductsForEvent(Party party){

        List<Pair<Product,Integer>> productsNeeded = new ArrayList<>();

        Product coke = new Product("Coke",20);

        productsNeeded.add(new Pair<>(coke,20));

        party.addProductsNeeded(productsNeeded);
    }


}