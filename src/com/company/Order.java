package com.company;

import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
public class Order {
    private String orderId;
    private List<Product> productList;
    private Date orderDate;

    public Order(String orderId, List<Product> productList, Date orderDate) {
        this.orderId = orderId;
        this.productList = productList;
        this.orderDate = orderDate;
    }
}
