package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.validationStatus.ValidEventStatus;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.validationStatus.ValidationStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Party extends Event{

    @Column
    private LocalDateTime confirmationDeadline;
    @Transient
    private ValidationStatus validationStatus;


    public Party(){
        this.validationStatus = new ValidEventStatus();
    }

    public List<Item> calculateShoppingList() {

        final Integer numberAtendees = attendees.size();

        return productsNeeded.stream().map(item -> new Item(item.getProduct(),item.getAmount()*numberAtendees)).collect(Collectors.toList());

    }

    public boolean hasNothingToBuy(List<Item> shoppingList) {

        return shoppingList.stream().allMatch(item -> item.getAmount() == 0);

    }

    public Double calculateCost() {

        return this.calculateShoppingList().stream().mapToDouble(item -> item.totalPrice()).sum();
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


    @Override
    public void acceptAttendee(User person) {
        this.validationStatus.acceptAttendee(person,this);
    }

    public boolean hasValidConfirmationDeadline() { return this.validationStatus.isValid(this);}


}
