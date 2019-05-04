package model.event;

import model.Item;
import model.Product;
import model.User;
import model.exceptions.ItemAlreadyReservedException;
import model.factory.EventFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {


    @Test
    public void aBasketHasProducts(){

        Basket basket = EventFactory.anyBasket();
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke",new Double(20));
        productsNeeded.add(new Item(coke,10));

        basket.addProductsNeeded(productsNeeded);

        assertEquals(productsNeeded, basket.getProductsNeeded());
    }

    @Test
    public void aBasketCanAcceptAnAttendee(){

        Basket basket = EventFactory.anyBasket();
        User person = new User();
        basket.acceptAttendee(person);

        assertTrue(basket.isAttending(person));
    }

    @Test
    public void aBasketCanReturnTheProductsMissing() {
        Basket basket = EventFactory.anyBasket();
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke", 20d);
        productsNeeded.add(new Item(coke,10));
        basket.addProductsNeeded(productsNeeded);

        assertEquals(productsNeeded, basket.productsMissing());
    }

    @Test
    public void aBasketCanReserveAProduct() throws ItemAlreadyReservedException {
        Basket basket = EventFactory.anyBasket();
        User person = new User();
        basket.acceptAttendee(person);
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke", 20d);
        Item cokeItem = new Item(coke,10);
        productsNeeded.add(cokeItem);
        basket.addProductsNeeded(productsNeeded);

        basket.reserve(cokeItem, person);

        assertTrue(basket.productsMissing().isEmpty());
    }

    @Test(expected = ItemAlreadyReservedException.class)
    public void aItemCantBeReservedIfItIsAlreadyReserved() throws ItemAlreadyReservedException {

        Basket basket = EventFactory.anyBasket();
        User personA = setAttendeeForBasket(basket);
        User personB = setAttendeeForBasket(basket);

        setProductNeededForEvent();
        List<Item> productsNeeded = new ArrayList<>();
        Product coke = new Product("Coke", 20d);
        Item cokeItem = new Item(coke,10);
        productsNeeded.add(cokeItem);
        basket.addProductsNeeded(productsNeeded);

        basket.reserve(cokeItem, personA);
        basket.reserve(cokeItem, personB);
    }

    private void setProductNeededForEvent() {
    }


    private User setAttendeeForBasket(Basket basket) {
        User person = new User();
        basket.acceptAttendee(person);
        return person;
    }

}
