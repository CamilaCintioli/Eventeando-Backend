package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model;

public class Item {
    private Product product;
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
