package com.company;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private String productId;
    private String name;
    private String manufacturer;
    private String productionDate;
    private String model;
    private double purchasePrice;
    private double retailPrice;
    private int quantity;

    public Product(String productId, String name, String manufacturer, String productionDate, String model, double purchasePrice, double retailPrice, int quantity) {
        this.productId = productId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    public Product(String productId, String name, String manufacturer, String productionDate, int i, double retailPrice, int quantity) {
        this.productId = productId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    public String toFileFormat() {
        return productId + "," + name + "," + manufacturer + "," + model + "," + purchasePrice + "," + retailPrice + "," + quantity;
    }
}

