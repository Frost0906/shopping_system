package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public final class Customer extends User {


    private String level = "铜牌客户";
    private Date registrationDate = new Date();
    private double totalExpenditure = 0.0;
    private List<Order> orderHistory = new ArrayList<>();
    private ShoppingCart cart = new ShoppingCart();

    public Customer() {}
    public Customer(String username, String password, String phoneNumber, String email, String userId) {
        super(username, password, userId);
    }

    public Customer(String username, String password, String userId, String email) {
        super(username, password, userId, email);
    }

    public Customer(String username, String password, String phoneNumber, String email, String level, Date registrationDate, double totalExpenditure) {
        super(username, password, phoneNumber, email);
        this.level = level;
        this.registrationDate = registrationDate;
        this.totalExpenditure = totalExpenditure;
    }

    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
        System.out.println("Added to cart.");
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
        System.out.println("Removed from cart.");
    }

    public void checkout() {
        System.out.println("Select a checkout method: ");
        System.out.println("1. Alipay");
        System.out.println("2. Wechat");
        System.out.println("3. Bankcard");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                cart.checkout();
                Payment.alipay();
                break;
            case 2:
                cart.checkout();
                Payment.wechat();
                break;
            case 3:
                cart.checkout();
                Payment.bankcard();
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    public void viewOrderHistory() {
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }
}


