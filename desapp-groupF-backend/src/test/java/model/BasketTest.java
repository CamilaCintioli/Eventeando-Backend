package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {


    @Test
    public void aPartyHasProducts(){

        Basket basket = new Basket();
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke",new Double(20));
        productsNeeded.add(new Item(coke,10));
        basket.addProductsNeeded(productsNeeded);

        assertEquals(productsNeeded, basket.getProductsNeeded());
    }

    @Test
    public void aBasketCanAcceptAnAttendee(){

        Basket basket = new Basket();
        User person = new User();
        basket.acceptAttendee(person);

        assertTrue(basket.isAttending(person));
    }

    @Test
    public void aBasketCanReturnTheProductsMissing() {
        Basket basket = new Basket();
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke",new Double(20));
        productsNeeded.add(new Item(coke,10));
        basket.addProductsNeeded(productsNeeded);

        assertEquals(productsNeeded, basket.productsMissing());
    }

    @Test
    public void aBasketCanReserveAProduct() throws ItemAlreadyReservedException {
        Basket basket = new Basket();
        User person = new User();
        basket.acceptAttendee(person);
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke",new Double(20));
        Item cokeItem = new Item(coke,10);
        productsNeeded.add(cokeItem);
        basket.addProductsNeeded(productsNeeded);

        basket.reserve(cokeItem, person);

        assertTrue(basket.productsMissing().isEmpty());
    }

    @Test(expected = ItemAlreadyReservedException.class)
    public void aItemCantBeReservedIfItIsAlreadyReserved() throws ItemAlreadyReservedException {

        Basket basket = new Basket();
        User personA = new User();
        User personB = new User();
        basket.acceptAttendee(personA);
        basket.acceptAttendee(personB);
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke",new Double(20));
        Item cokeItem = new Item(coke,10);
        productsNeeded.add(cokeItem);
        basket.addProductsNeeded(productsNeeded);

        basket.reserve(cokeItem, personA);
        basket.reserve(cokeItem, personB);
    }

}
