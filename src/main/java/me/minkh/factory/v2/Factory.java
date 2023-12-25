package me.minkh.factory.v2;

public class Factory {

    public static Product createProduct(String name) {
        return new Product(name);
    }
    public static Product createProduct() {
        return new Product();
    }

}
