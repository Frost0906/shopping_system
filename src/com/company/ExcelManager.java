package com.company;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

class ExcelManager {

    private static final String PRODUCTS_FILE = "C:\\Users\\Aurora\\Desktop\\products.xls";
    private static final String CUSTOMERS_FILE = "C:\\Users\\Aurora\\Desktop\\customers.xls";
    private static final String ORDERS_FILE = "C:\\Users\\Aurora\\Desktop\\orders.xls";

    public static void saveProductsToExcel(Map<String, Product> products) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Products");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Manufacturer");
        header.createCell(3).setCellValue("Model");
        header.createCell(4).setCellValue("Production Date");
        header.createCell(5).setCellValue("Purchase Price");
        header.createCell(6).setCellValue("Retail Price");
        header.createCell(7).setCellValue("Quantity");

        int rowNum = 1;
        for (Product product : products.values()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getManufacturer());
            row.createCell(3).setCellValue(product.getModel());
            row.createCell(4).setCellValue(product.getProductionDate());
            row.createCell(5).setCellValue(product.getPurchasePrice());
            row.createCell(6).setCellValue(product.getRetailPrice());
            row.createCell(7).setCellValue(product.getQuantity());
        }

        try (FileOutputStream fileOut = new FileOutputStream(PRODUCTS_FILE)) {
            workbook.write(fileOut);
            System.out.println("Products saved to Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Product> loadProductsFromExcel() {
        Map<String, Product> products = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(PRODUCTS_FILE)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("Products");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String productId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String manufacturer = row.getCell(2).getStringCellValue();
                String model = row.getCell(3).getStringCellValue();
                String produtionDate = row.getCell(4).getStringCellValue();
                double purchasePrice = row.getCell(5).getNumericCellValue();
                double retailPrice = row.getCell(6).getNumericCellValue();
                int quantity = (int) row.getCell(7).getNumericCellValue();

                Product product = new Product(productId, name, manufacturer, model, produtionDate, purchasePrice, retailPrice, quantity);
                products.put(productId, product);
            }
            System.out.println("Products loaded from Excel.");
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("Products Excel file not found, starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void saveCustomersToExcel(Map<String, Customer> customers) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Customers");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("User ID");
        header.createCell(1).setCellValue("Username");
        header.createCell(2).setCellValue("Email");

        int rowNum = 1;
        for (Customer customer : customers.values()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getUserId());
            row.createCell(1).setCellValue(customer.getUsername());
            row.createCell(2).setCellValue(customer.getEmail());
        }

        try (FileOutputStream fileOut = new FileOutputStream(CUSTOMERS_FILE)) {
            workbook.write(fileOut);
            System.out.println("Customers saved to Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Customer> loadCustomersFromExcel() {
        Map<String, Customer> customers = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(CUSTOMERS_FILE)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("Customers");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String userId = row.getCell(0).getStringCellValue();
                String username = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String password = "defaultPassword"; // You might want to store passwords encrypted.

                Customer customer = new Customer(username, password, userId, email);
                customers.put(userId, customer);
            }
            System.out.println("Customers loaded from Excel.");
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("Customers Excel file not found, starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void saveOrdersToExcel(List<Order> orders) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Orders");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Order ID");
        header.createCell(1).setCellValue("Order Date");
        header.createCell(2).setCellValue("Products");

        int rowNum = 1;
        for (Order order : orders) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getOrderId());
            row.createCell(1).setCellValue(order.getOrderDate().toString());
            row.createCell(2).setCellValue(order.getProductList().toString());
        }

        try (FileOutputStream fileOut = new FileOutputStream(ORDERS_FILE)) {
            workbook.write(fileOut);
            System.out.println("Orders saved to Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Order> loadOrdersFromExcel() {
        List<Order> orders = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(ORDERS_FILE)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("Orders");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String orderId = row.getCell(0).getStringCellValue();
                String orderDate = row.getCell(1).getStringCellValue();
                // In a real-world scenario, you'd deserialize the product list instead of using a simple string
                List<Product> products = new ArrayList<>();

                Order order = new Order(orderId, products, new Date()); // Simplified for example
                orders.add(order);
            }
            System.out.println("Orders loaded from Excel.");
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("Orders Excel file not found, starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
}

