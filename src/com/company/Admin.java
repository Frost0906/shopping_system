package com.company;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

@Getter
@Setter
public final class Admin extends User implements ProductManager, CustomerManager {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    private final String customerFile = "C:\\Users\\Aurora\\Desktop\\Customers.txt";
    private final String productFile = "C:\\Users\\Aurora\\Desktop\\Products.txt";

    public Admin(String username, String password) {
        super(username, password);
//        this.customers = ExcelManager.loadCustomersFromExcel();
//        this.products = ExcelManager.loadProductsFromExcel();
    }

    @Override
    public boolean login(String username, String password) throws Exception {
        if (username.equals(this.getUsername()) && password.equals(this.getPassword())) {
            System.out.println("Login successfully!");
            return true;
        } else {
            throw new Exception("Incorrect username or password!");
        }
    }

    public void resetCustomerPassword(String customerEmail) {
        Customer customer = customers.get(customerEmail);
        if (customer != null) {
            String newPassword = generateRandomPassword();
            customer.changePassword(newPassword);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void listCustomers() {
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getUserId(), customer);
        System.out.println("Customer added successfully!");
    }

    public void removeCustomer(String customerId) {
        if (customers.containsKey(customerId)) {
            System.out.println("Are you sure you want to delete this customer? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                customers.remove(customerId);
                System.out.println("Customer removed.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void queryCustomer(String keyword) {
        for (Customer customer : customers.values()) {
            if (customer.getUsername().contains(keyword) || customer.getUserId().equals(keyword)) {
                System.out.println(customer);
            }
        }
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product added successfully!");
    }

    @Override
    public void deleteProduct(String productId) {
        if (products.containsKey(productId)) {
            System.out.println("Are you sure you want to delete this product? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                products.remove(productId);
                System.out.println("Product removed.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void modifyProduct(String productId, Product newProduct) {
        if (products.containsKey(productId)) {
            products.put(productId, newProduct);
            System.out.println("Product modified successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void listProducts() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    @Override
    public void queryProduct(String name, String manufacturer, double minPrice) {
        for (Product product : products.values()) {
            if (product.getName().contains(name) && product.getManufacturer().contains(manufacturer) &&
                    product.getRetailPrice() > minPrice) {
                System.out.println(product);
            }
        }
    }

    public void saveDataToDatabase() throws SQLException {
        DatabaseManager.saveAdmin(this);
        for (Customer customer : customers.values()) {
            DatabaseManager.saveCustomer(customer);
        }
        for (Product product : products.values()) {
            DatabaseManager.saveProduct(product);
        }
    }

    public void loadDataFromDatabase() throws SQLException {
        this.customers = DatabaseManager.loadCustomers();
        this.products = DatabaseManager.loadProducts();
    }
}
