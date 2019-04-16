package model;

public class Item {
    private Product product;
    private Integer amount;

    public Item (Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public float price() {
        return product.price * amount;
    }
}
