package model;

import model.event.Collect;
import model.exceptions.CollectionHasntReachedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CollectTest {

    private void setProductsForEvent(Collect collect){

        List<Item> productsNeeded = new ArrayList<>();

        Product coke = new Product("Coke",new Double(20));

        productsNeeded.add(new Item(coke,10));

        collect.addProductsNeeded(productsNeeded);
    }

    @Test
    public void aCollectCanAcceptAnAttendee(){

        Collect collect = new Collect();
        User person = new User();
        collect.acceptAttendee(person);

        assertTrue(collect.isAttending(person));
    }

    @Test
    public void aCollectCalculateChargePerAttendeeWith2Attendee() {

        Collect collect = new Collect();
        setProductsForEvent(collect);
        User personA = new User();
        User personB = new User();
        collect.acceptAttendee(personA);
        collect.acceptAttendee(personB);

        assertEquals( new Double(100) , collect.chargePerAttendee());
    }

    @Test
    public void aCollectCalculateChargePerAttendeeWithNoneAttendee() {

        Collect collect = new Collect();
        assertEquals( new Double(0), collect.chargePerAttendee());
    }

    @Test
    public void aCollectTotalCost() {

        Collect collect = new Collect();
        setProductsForEvent(collect);

        assertEquals(new Double(200), collect.totalCost());
    }

    @Test
    public void aCollectCanBePayed() {

        Collect collect = new Collect();
        setProductsForEvent(collect);
        User personA = new User();
        User personB = new User();
        collect.acceptAttendee(personA);
        collect.acceptAttendee(personB);

        collect.pay(personA, new Double(100));

        assertEquals(new Double(100), collect.pendingCost());
    }

    @Test
    public void aPersonCanExtractOnceTheCollectionHasBeenReached() throws CollectionHasntReachedException {
        Collect collect = new Collect();
        setProductsForEvent(collect);
        User personA = new User();
        collect.acceptAttendee(personA);

        collect.pay(personA, new Double(200));

        assertEquals(new Double(200), collect.extractAll(personA));
    }

    @Test(expected = CollectionHasntReachedException.class)
    public void aPersonCantExtractIfTheCollectionHasntBeenReached() throws CollectionHasntReachedException {
        Collect collect = new Collect();
        setProductsForEvent(collect);
        User personA = new User();
        collect.acceptAttendee(personA);

        collect.pay(personA, new Double(100));

        collect.extractAll(personA);
    }

}


