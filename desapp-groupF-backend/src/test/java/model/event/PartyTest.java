package model.event;

import model.Item;
import model.Product;
import model.User;
import model.exceptions.DeadlineToConfirmAttendanceHasPassed;

import model.factory.EventFactory;
import org.junit.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PartyTest {

    private List<Item> productsNeeded = new ArrayList<>();
    private Product coke = new Product("Coke", 20d);
    private Product bread = new Product("Bread", 30d);


    @Test
    public void aPartyHasProducts(){

        Party party = EventFactory.anyParty();
        Item oneCoke = new Item(coke,1);
        productsNeeded.add(oneCoke);

        party.addProductsNeeded(productsNeeded);

        assertEquals(1,party.getProductsNeeded().size());
        assertTrue(party.getProductsNeeded().contains(oneCoke));
    }

    @Test
    public void aPartyCalculatesShoppingListWithNoAttendeesSoItHasNothingToBuy(){

        Party party = EventFactory.anyParty();

        List<Item> shoppingList = party.calculateShoppingList();

        assertTrue(party.hasNothingToBuy(shoppingList));
    }

    @Test
    public void aPartyThatNeedsACokePerAttendeeAndHasTwoAttendeesWhenTheShoppingListIsCalculatedTwoCokesAreNeeded() {

        Party party = EventFactory.anyParty();
        setAttendeesForEvent(party,2);
        setProductForEvent(party, new Item(coke,1));

        List<Item> shoppingList = party.calculateShoppingList();

        assertEquals(2,shoppingList.get(0).getAmount(),0);
    }



    @Test
    public void aPartyCalculatesFinalCostWithTwoAttendeesWithAProductThatCostsTwentyPesos(){


        Party party = EventFactory.anyParty();
        setAttendeesForEvent(party,2);

        Product coke = new Product("Coke", 20d);
        setProductForEvent(party, new Item(coke,1));

        assertEquals(40,party.calculateCost(),0);

    }

    @Test
    public void aPartyCalculatesFinalCostForTwoAttendeesAndTwoProducts(){

        Party party = EventFactory.anyParty();
        List<Item> products = Arrays.asList(new Item(coke,1),new Item(bread,2));

        setAttendeesForEvent(party,2);
        party.addProductsNeeded(products);

        assertEquals(160,party.calculateCost(),0);
    }

    @Test
    public void aPartyHasAValidConfirmationDeadline(){

        Party party = EventFactory.anyParty();
        LocalDateTime confirmationDeadline = LocalDateTime.now().plusHours(1);
        party.setDeadlineConfirmation(confirmationDeadline);

        assertTrue(party.hasValidConfirmationDeadline());
    }

    @Test
    public void aPartyHasAnInvalidConfirmationDeadlineAndItsStatusIsInvalid(){

        Party party = EventFactory.anyParty();
        LocalDateTime invalidConfirmationDeadline = LocalDateTime.now().minusHours(1);
        party.setDeadlineConfirmation(invalidConfirmationDeadline);


        assertFalse(party.hasValidConfirmationDeadline());

    }

    @Test
    public void aUserCanAttendAPartyWithAValidConfirmationDeadline(){

        Party party = EventFactory.anyParty();
        LocalDateTime confirmationDeadline = LocalDateTime.now().plusHours(1);
        party.setDeadlineConfirmation(confirmationDeadline);
        User user = new User();

        party.acceptAttendee(user);

        assertTrue(party.isAttending(user));

    }



    @Test(expected = DeadlineToConfirmAttendanceHasPassed.class)
    public void aUserCantAttendAPartyWithAnInvalidConfirmationDeadline(){

        Party party = EventFactory.anyParty();
        LocalDateTime confirmationDeadline = LocalDateTime.now().minusHours(1);
        party.setDeadlineConfirmation(confirmationDeadline);
        User user = new User();

        party.acceptAttendee(user);
    }

    @Test
    public void aNewPartyIsCreatedAndSendMailToThePersonsInvited()  {

        //TODO: test sending mail to user.
 
    }


    private void setAttendeesForEvent(Party party,Integer numberOfAttendees) {

        Integer numOfAttendees = numberOfAttendees;

        while(numOfAttendees>0){
            User user = new User();
            party.acceptAttendee(user);
            numOfAttendees -= 1;
        }
    }


    private void setProductForEvent(Party party, Item item) {

        List<Item> products = Arrays.asList(item);
        party.addProductsNeeded(products);

    }

}
