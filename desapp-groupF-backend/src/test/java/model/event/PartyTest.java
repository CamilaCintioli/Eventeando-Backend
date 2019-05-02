package model.event;

import model.Item;
import model.Product;
import model.User;
import model.event.Party;
import model.exceptions.DeadlineToConfirmAttendanceHasPassed;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PartyTest {

    private Party party = new Party();
    private User user1 = new User();
    private User user2 = new User();
    private List<Item> productsNeeded = new ArrayList<>();
    private Product coke = new Product("Coke", 20d);
    private Product bread = new Product("Bread", 30d);

    @Before
    public void setUp(){
        party.setDeadlineConfirmation(LocalDateTime.now().plusHours(1));
    }

    @Test
    public void aPartyHasProducts(){

        Item oneCoke = new Item(coke,1);
        productsNeeded.add(oneCoke);

        party.addProductsNeeded(productsNeeded);

        assertEquals(1,party.getProductsNeeded().size());
        assertTrue(party.getProductsNeeded().contains(oneCoke));
    }

    @Test
    public void aPartyCalculatesShoppingListWithNoAttendeesAndHasNothingToBuy(){

        List<Item> products = new ArrayList<>();
        products.add(new Item(coke,1));
        setProductsForEvent(party, products);

        List<Item> shoppingList = party.calculateShoppingList();

        assertTrue(party.hasNothingToBuy(shoppingList));
    }

    @Test
    public void aPartyCalculatesShoppingListWithTwoAttendees() {

        List<User> attendees = new ArrayList<>();
        attendees.add(user1);
        attendees.add(user2);
        setAttendeesForEvent(party,attendees);
        setProductsForEvent(party);

        List<Item> shoppingList = party.calculateShoppingList();

        Integer cokeQuantity = shoppingList.get(0).getAmount();

        assertEquals(2,cokeQuantity,0);

    }


    @Test
    public void aPartyCalculatesFinalCostForTwoAttendeesWithAProductCostTwenty(){

        List<User> attendees = new ArrayList<>();
        attendees.add(user1);
        attendees.add(user2);

        setAttendeesForEvent(party,attendees);
        setProductsForEvent(party);

        assertEquals(40,party.calculateCost(),0);


    }

    @Test
    public void aPartyCalculatesFinalCostForTwoAttendeesAndTwoProducts(){

        List<User> attendees = new ArrayList<>();
        attendees.add(user1);
        attendees.add(user2);

        List<Item> products = new ArrayList<>();
        products.add(new Item(coke,1));
        products.add(new Item(bread,2));

        setAttendeesForEvent(party,attendees);
        setProductsForEvent(party,products);

        assertEquals(160,party.calculateCost(),0);
    }

    @Test
    public void aPartyHasAValidConfirmationDeadline(){

        LocalDateTime confirmationDeadline = LocalDateTime.now().plusHours(1);

        party.setDeadlineConfirmation(confirmationDeadline);

        assertTrue(party.isValid());
        assertFalse(party.isInvalid());

    }

    @Test
    public void aPartyHasAnInvalidConfirmationDeadlineAndItsStatusIsInvalid(){

        LocalDateTime confirmationDeadline = LocalDateTime.now().minusHours(1);

        party.setDeadlineConfirmation(confirmationDeadline);

        party.checkValidity();


        assertTrue(party.isInvalid());
        assertFalse(party.isValid());

    }

    @Test
    public void aUserCanAttendAPartyWithAValidConfirmationDeadline(){

        LocalDateTime confirmationDeadline = LocalDateTime.now().plusHours(1);
        party.setDeadlineConfirmation(confirmationDeadline);

        party.acceptAttendee(user1);

        assertTrue(party.isAttending(user1));

    }



    @Test(expected = DeadlineToConfirmAttendanceHasPassed.class)
    public void aUserCantAttendAPartyWithAnInvalidConfirmationDeadline(){

        LocalDateTime confirmationDeadline = LocalDateTime.now().minusHours(1);
        party.setDeadlineConfirmation(confirmationDeadline);

        party.acceptAttendee(user1);
    }

    @Test
    public void aNewPartyIsCreatedAndSendMailToThePersonsInvited()  {


        List<User> guests = new ArrayList<>();
        //user1.setEmail("camila.cintioli@gmail.com");
        //guests.add(user1);

        party.setGuests(guests);
    }


    @After
    public void tearDown(){
        productsNeeded = new ArrayList<>();
    }

    private void setAttendeesForEvent(Party party, List<User> attendees) {

        for(User attendee:attendees){
            party.acceptAttendee(attendee);
        }
    }

    private void setProductsForEvent(Party party, List<Item> products){

        party.addProductsNeeded(products);
    }

    private void setProductsForEvent(Party party) {

        List<Item> products = new ArrayList<>();
        products.add(new Item(coke,1));
        setProductsForEvent(party, products);

    }


}
