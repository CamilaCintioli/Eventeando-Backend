package model.event;

import model.Item;
import model.Product;
import model.User;
import model.exceptions.CollectionHasntReachedException;
import model.factory.EventFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectTest {

    private void setProductsForEvent(Collect collect){

        List<Item> productsNeeded = new ArrayList<>();

        Product coke = new Product("Coke",new Double(20));

        productsNeeded.add(new Item(coke,10));

        collect.addProductsNeeded(productsNeeded);
    }

    @Test
    public void aCollectCanAcceptAnUserAsAnAttendee(){

        Collect collect = EventFactory.anyCollect();
        User person = new User();
        collect.acceptAttendee(person);

        assertTrue(collect.isAttending(person));
    }

    @Test
    public void aCollectCanCalculateChargePerAttendeeWithTwoAttendees() {

        Collect collect = EventFactory.anyCollect();
        setProductsForEvent(collect);
        setAttendeesForEvent(collect, 2);

        assertEquals( 100d , collect.chargePerAttendee(),0);
    }

    @Test
    public void aCollectWithNoAttendeesHasCeroChargePerAttendee() {

        Collect collect = EventFactory.anyCollect();

        assertEquals( 0d, collect.chargePerAttendee(),0);
    }

    @Test
    public void aCollectTotalCost() {

        Collect collect = EventFactory.anyCollect();
        setProductsForEvent(collect);

        assertEquals(200d, collect.totalCost(),0);
    }

    @Test
    public void aCollectCanBePayed() {

        Collect collect = EventFactory.anyCollect();
        setProductsForEvent(collect);
        User person = setAttendeeForCollect(collect);

        collect.pay(person, 100d);

        assertEquals(100d, collect.pendingCost(),0);
    }

    @Test
    public void aPersonCanExtractOnceTheCollectionHasBeenReached() throws CollectionHasntReachedException {
        Collect collect = EventFactory.anyCollect();
        setProductsForEvent(collect);
        User person = setAttendeeForCollect(collect);

        collect.pay(person, 200d);

        assertEquals(200d, collect.extractAll(person),0);
    }

    @Test(expected = CollectionHasntReachedException.class)
    public void aPersonCantExtractIfTheCollectionHasntBeenReached() throws CollectionHasntReachedException {
        Collect collect = EventFactory.anyCollect();
        setProductsForEvent(collect);
        User person = setAttendeeForCollect(collect);

        collect.pay(person, 100d);

        collect.extractAll(person);
    }

    private void setAttendeesForEvent(Collect collect,Integer numberOfAttendees) {

        Integer numOfAttendees = numberOfAttendees;

        while(numOfAttendees>0){
            User user = new User();
            collect.acceptAttendee(user);
            numOfAttendees -= 1;
        }
    }

    private User setAttendeeForCollect(Collect collect) {
        User person = new User();
        collect.acceptAttendee(person);
        return person;
    }

}


