package me.minkh.factorymethod.idcard;

import me.minkh.factorymethod.framework.Factory;
import me.minkh.factorymethod.framework.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDCardFactory extends Factory {

    private final Map<String, Integer> store = new HashMap<>();
    private int serialNo = 100;

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner, serialNo++);
    }

    @Override
    protected void registerProduct(Product product) {
        IDCard idCard = (IDCard) product;
        store.put(idCard.getOwner(), idCard.getSerialNo());
    }

    public Map<String, Integer> getStore() {
        return store;
    }

}
