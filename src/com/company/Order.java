package com.company;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
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
