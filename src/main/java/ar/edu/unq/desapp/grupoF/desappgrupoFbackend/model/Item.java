package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    private Product product;
    @Column
    private Integer amount;

    public Item (Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public Double totalPrice() {
        return product.price * amount;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getAmount(){
        return this.amount;
    }


}
