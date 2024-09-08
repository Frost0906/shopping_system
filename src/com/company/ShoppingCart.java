package com.company;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> cartItems = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        cartItems.put(product, quantity);
    }

    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    public void checkout() {
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
            System.out.println("Bought " + quantity + " of " + product.getName());
        }
        cartItems.clear();
    }
}
