package com.company;

public interface CustomerManager {
    void listCustomers();
    void addCustomer(Customer customer);
    void removeCustomer(String customerId);
    void queryCustomer(String keyword);
}
