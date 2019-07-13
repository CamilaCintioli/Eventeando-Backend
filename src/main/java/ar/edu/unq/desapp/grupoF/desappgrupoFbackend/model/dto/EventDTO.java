package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Item;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Basket;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Party;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EventDTO {

    Long id;
    @NotBlank
    String name;
    String description;
    List<Item> productsNeeded = new ArrayList<Item>();
    List<@Email(message = "The guests emails should be valid") String> guestsMails;
    @NotNull
    String eventType;
    List<String> attendees;
    LocalDateTime deadlineConfirmation;
    @Future
    LocalDateTime dayOfEvent;
    Long attendeesCounter;
    List<String> reservedProducts;
    @Email(message = "Email should be valid")
    String creatorEmail;
    List<Item> missingProducts;

    public EventDTO(){}

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.productsNeeded = event.getProductsNeeded();
        this.guestsMails = event.getGuests();
        this.eventType = event.getClass().getSimpleName();
        this.attendees = event.getAttendees().stream().map(user -> user.getEmail()).collect(Collectors.toList());
        this.creatorEmail = event.getCreatorEmail();
        if(this.eventType.equals("Party")){
            this.deadlineConfirmation = ((Party) event).getConfirmationDeadline();
        }
        this.attendeesCounter = event.getAttendeesCounter();
        this.dayOfEvent = event.getDayOfEvent();
        if(this.eventType.equals("Basket")){
            this.reservedProducts = ((Basket) event).getProductsReserved().stream().map(item -> item.getProduct().getName() + " - " + ((Basket) event).reservas().get(item).getEmail()).collect(Collectors.toList());
            this.missingProducts =  ((Basket) event).productsMissing();
        }



    }

    public void setProductsNeeded(List<Item> productsNeeded) {

        for(Item i : productsNeeded){
            if(i.getProduct()==null||i.getProduct().getName()==null||i.getProduct().getPrice()==null||i.getAmount()==null){
                throw new RuntimeException("Products needed only accept items");
            }
        }

        this.productsNeeded = productsNeeded;
    }

    public List<String> getGuestsMails() {
        return guestsMails;
    }

    public void setGuestsMails(List<String> guestsMails) {

        if(guestsMails == null || guestsMails.size()==0){
            throw new RuntimeException("There should be at least one guest to the event");
        }

        if(guestsMails.stream().filter(email -> this.checkEmail(email)).count() == guestsMails.size()){
            this.guestsMails = guestsMails;
        }
        else {
            throw new RuntimeException("The guests mails are not valid");
        }

    }

    public List<Item> getProductsNeeded() {
        return productsNeeded;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType){
        if(eventType.equals("Party")||eventType.equals("Basket")||eventType.equals("Collect")){
            this.eventType = eventType;
        }
        else {
            throw new RuntimeException("The type: " + eventType + " is not valid");
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null){
            throw new RuntimeException("The event name should be given");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttendees(List<String> emailsAttendees){

        if(emailsAttendees.stream().filter(email -> this.checkEmail(email)).count() == emailsAttendees.size()){
            this.attendees = emailsAttendees;
        }
        else {
            throw new RuntimeException("The attendees mails are not valid");
        }
    }

    public List<String> getAttendees(){
        return this.attendees;
    }

    public LocalDateTime getDeadlineConfirmation() {
        return this.deadlineConfirmation;
    }

    public void setDeadlineConfirmation(String date){
        //2019-06-27

        String dateTime = date + " 00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);

        this.deadlineConfirmation= formatDateTime;

    }

    public void setAttendeesCounter(Long attendeesCounter){
        this.attendeesCounter = attendeesCounter;
    }

    public Long getAttendeesCounter(){
        return this.attendeesCounter;
    }

    public void setDayOfEvent(String date){

        if(date==null){
            throw new RuntimeException("The event needs a day");
        }


        String dateTime = date + " 00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);

        this.dayOfEvent= formatDateTime;

    }

    public LocalDateTime getDayOfEvent(){
        return this.dayOfEvent;
    }

    public void setReservedProducts(List<String> productsNameReserved){
        this.reservedProducts = productsNameReserved;
    }

    public List<String> getReservedProducts(){
        return this.reservedProducts;
    }

    private boolean checkEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public void setCreatorEmail(String email){

        //if(!this.checkEmail(email)|| email == null) throw new RuntimeException("The mail of the creator is not valid.");
        this.creatorEmail = email;

    }

    public String getCreatorEmail(){
        return this.creatorEmail;
    }

    public List<Item> getMissingProducts(){
        return this.missingProducts;
    }

    public void setMissingProducts(List<Item> missingProducts){
        this.missingProducts = missingProducts;
    }

    @Override
    public String toString() {
        return String.format( "id: " + id + " | name: " + name + " | creatorEmail: " + creatorEmail + " | dayOfEvent: " + dayOfEvent );
    }

}
