package model;

public class Item {
    private Product product
    private Integer amount;

    public float price() {
        return product.price * amount;
    }
}
