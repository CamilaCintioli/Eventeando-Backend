package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

public class Product {

    String name;
    Double price;

    public Product(String name, Double price){
        this.name=name;
        this.price=price;
    }

    public Double getPrice() {
        return price;
    }
}
