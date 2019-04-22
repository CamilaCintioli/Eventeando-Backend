package model;

import model.validationStatus.ValidEventStatus;
import model.validationStatus.ValidationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Party {
    private List<User> attendees= new ArrayList<>();
    private List<Item> productsNeeded = new ArrayList<>();
    private LocalDateTime confirmationDeadline;
    private ValidationStatus validationStatus = new ValidEventStatus();

    public boolean isAttending(User person) {
        return this.attendees.contains(person);
    }

    public List<Item> calculateShoppingList() {

        final Integer numberAtendees = attendees.size();

        return productsNeeded.stream().map(item -> new Item(item.getProduct(),item.getAmount()*numberAtendees)).collect(Collectors.toList());

    }

    public void addProductsNeeded(List<Item> productsNeeded) {

        this.productsNeeded=productsNeeded;

    }

    public List<Item> getProductsNeeded() {

        return this.productsNeeded;
    }

    public boolean hasNothingToBuy(List<Item> shoppingList) {

        return shoppingList.stream().allMatch(item -> item.getAmount() == 0);

    }

    public Double calculateCost() {

        return this.calculateShoppingList().stream().mapToDouble(item -> item.getAmount()* item.getProduct().getPrice()).sum();
    }

    public void setDeadlineConfirmation(LocalDateTime confirmationDeadline) {
        this.confirmationDeadline = confirmationDeadline;
    }

    public LocalDateTime getConfirmationDeadline() {
        return  this.confirmationDeadline;
    }

    public void setValidationStatus(ValidationStatus status){
        this.validationStatus = status;
    }

    public void addAttendee(User person) {
        this.attendees.add(person);
    }


    public void acceptAttendee(User person) {
        this.checkValidity();
        this.validationStatus.acceptAttendee(person,this);
    }

    public void checkValidity(){
        this.isValid();
    }

    public boolean isValid() {
        return this.validationStatus.isValid(this);
    }

    public boolean isInvalid() {
        return this.validationStatus.isInvalid(this);
    }

}
