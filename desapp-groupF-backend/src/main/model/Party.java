package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Party {
    List<User> attendees= new ArrayList<User>();
    List<Pair<Product,Integer>> productsNeeded = new ArrayList<Pair<Product, Integer>>();

    public void acceptAttendee(User person) {
        this.attendees.add(person);
    }

    public boolean isAttending(User person) {
        return this.attendees.contains(person);
    }

    public List<Pair<Product, Integer>> calculateShoppingList() {


        final Integer numberAtendees = attendees.size();


       return productsNeeded.stream().map(pair -> new Pair<Product,Integer>(pair.getKey(),pair.getValue()*numberAtendees)).collect(Collectors.toList());

    }

    public void addProductsNeeded(List<Pair<Product,Integer>> productsNeeded) {

        this.productsNeeded=productsNeeded;

    }

    public List<Pair<Product,Integer>> getProductsNeeded() {

        return this.productsNeeded;
    }

    public boolean hasNothingToBuy(List<Pair<Product, Integer>> shoppingList) {

        return shoppingList.stream().allMatch(pair -> pair.getValue() == 0);

    }
}
