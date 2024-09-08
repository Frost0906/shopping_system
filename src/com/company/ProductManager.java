package com.company;

public interface ProductManager {
    public void listProducts();
    public void addProduct(Product product);
    public void modifyProduct(String productId, Product newProduct);
    public void deleteProduct(String productId);
    public void queryProduct(String name, String manufacturer, double minPrice);
    }
