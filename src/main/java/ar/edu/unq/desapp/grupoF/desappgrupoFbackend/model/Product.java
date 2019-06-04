package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    long id;
    @Column
    String name;
    @Column
    Double price;

    public Product(String name, Double price){
        this.name=name;
        this.price=price;
    }

    public Product(){}

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return this.name;
    }
}
