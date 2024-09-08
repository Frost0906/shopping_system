package com.company;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shopping_system";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void saveAdmin(Admin admin) throws SQLException {
        try (Connection conn = getConnection()) {
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, admin.getUsername());
                stmt.setString(2, admin.getPassword());
                stmt.executeUpdate();
            }
        }
    }

    public static Admin loadAdmin() throws SQLException {
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM admin WHERE admin_id = 1";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(rs.getString("username"), rs.getString("password"));
                }
            }
        }
        return null;
    }

    public static void saveCustomer(Customer customer) throws SQLException {
        try (Connection conn = getConnection()) {
            String query = "INSERT INTO customer (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, customer.getUsername());
                stmt.setString(2, customer.getPassword());
                stmt.setString(3, customer.getEmail());
                stmt.executeUpdate();
            }
        }
    }

    public static Map<String, Customer> loadCustomers() throws SQLException {
        Map<String, Customer> customers = new HashMap<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM customer";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String userId = String.valueOf(rs.getInt("customer_id"));
                    Customer customer = new Customer(
                            rs.getString("username"),
                            rs.getString("password"),
                            userId,
                            rs.getString("email")
                    );
                    customers.put(userId, customer);
                }
            }
        }
        return customers;
    }

    public static void saveProduct(Product product) throws SQLException {
        try (Connection conn = getConnection()) {
            String query = "INSERT INTO product (name, manufacturer, price, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getManufacturer());
                stmt.setDouble(3, product.getRetailPrice());
                stmt.setInt(4, product.getQuantity());
                stmt.executeUpdate();
            }
        }
    }

    public static Map<String, Product> loadProducts() throws SQLException {
        Map<String, Product> products = new HashMap<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM product";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String productId = String.valueOf(rs.getInt("product_id"));
                    Product product = new Product(
                            productId,
                            rs.getString("name"),
                            rs.getString("manufacturer"),
                            "",
                            0,
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    );
                    products.put(productId, product);
                }
            }
        }
        return products;
    }
}

