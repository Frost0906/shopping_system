package com.company;

import java.util.*;


public class ShoppingSystem {
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            Admin admin = new Admin("admin", "ynuinfo#777");
            Map<String, Customer> customers = new HashMap<>();
            Customer customer = new Customer();

            while (true) {
                System.out.println("Welcome to the Shopping System");
                System.out.println("Are you an Admin or Customer? (A/C)");
                String userType = scanner.nextLine();

                if (userType.equalsIgnoreCase("A")) {
                    handleAdminLogin(admin);
                } else if (userType.equalsIgnoreCase("C")) {
                    handleCustomerActions(customers, customer);
                }
            }
        }

        private static void handleAdminLogin(Admin admin) {
            System.out.println("Enter Admin username:");
            String username = scanner.nextLine();
            System.out.println("Enter Admin password:");
            String password = scanner.nextLine();

            try {
                if (admin.login(username, password)) {
                    System.out.println("Welcome Admin!");
                    adminMenu(admin);
                } else {
                    System.out.println("Invalid credentials!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void adminMenu(Admin admin) {
            while (true) {
                System.out.println("Admin Menu:");
                System.out.println("1. Manage Products");
                System.out.println("2. Manage Customers");
                System.out.println("3. Change Password");
                System.out.println("4. Logout");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        manageProducts(admin);
                        break;
                    case 2:
                        manageCustomers(admin);
                        break;
                    case 3:
                        System.out.println("Enter new password:");
                        String newPassword = scanner.nextLine();
                        admin.changePassword(newPassword);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }

        private static void manageProducts(Admin admin) {
            while (true) {
                System.out.println("Product Management:");
                System.out.println("1. List Products");
                System.out.println("2. Add Product");
                System.out.println("3. Remove Product");
                System.out.println("4. Modify Product");
                System.out.println("5. Search Products");
                System.out.println("6. Back");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        admin.listProducts();
                        break;
                    case 2:
                        System.out.println("Enter product details:");
                        Product product = createProduct();
                        admin.addProduct(product);
                        break;
                    case 3:
                        System.out.println("Enter product ID to remove:");
                        String productId = scanner.nextLine();
                        admin.deleteProduct(productId);
                        break;
                    case 4:
                        System.out.println("Enter product ID to modify:");
                        String modifyId = scanner.nextLine();
                        Product newProduct = createProduct();
                        admin.modifyProduct(modifyId, newProduct);
                        break;
                    case 5:
                        System.out.println("Enter search parameters (name, manufacturer, min price):");
                        String name = scanner.nextLine();
                        String manufacturer = scanner.nextLine();
                        double minPrice = Double.parseDouble(scanner.nextLine());
                        admin.queryProduct(name, manufacturer, minPrice);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        private static Product createProduct() {
            System.out.println("Enter product ID:");
            String productId = scanner.nextLine();
            System.out.println("Enter product name:");
            String name = scanner.nextLine();
            System.out.println("Enter manufacturer:");
            String manufacturer = scanner.nextLine();
            System.out.println("Enter productionDate:");
            String productionDate = scanner.nextLine();
            System.out.println("Enter model:");
            String model = scanner.nextLine();
            System.out.println("Enter purchase price:");
            double purchasePrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter retail price:");
            double retailPrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter quantity:");
            int quantity = Integer.parseInt(scanner.nextLine());

            return new Product(productId, name, manufacturer, productionDate, model, purchasePrice, retailPrice, quantity);
        }

        private static void manageCustomers(Admin admin) {
            while (true) {
                System.out.println("Customer Management:");
                System.out.println("1. List Customers");
                System.out.println("2. Remove Customer");
                System.out.println("3. Search Customers");
                System.out.println("4. Reset Customer Password");
                System.out.println("5. Back");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        admin.listCustomers();
                        break;
                    case 2:
                        System.out.println("Enter customer ID to remove:");
                        String customerId = scanner.nextLine();
                        admin.removeCustomer(customerId);
                        break;
                    case 3:
                        System.out.println("Enter search key word:");
                        String keyword = scanner.nextLine();
                        admin.queryCustomer(keyword);
                        break;
                    case 4:
                        System.out.println("Please enter customer's email:");
                        String customerEmail = scanner.nextLine();
                        admin.resetCustomerPassword(customerEmail);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        private static void handleCustomerActions(Map<String, Customer> customers, Customer customer) {
            System.out.println("Customer Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerCustomer(customers, customer);
                    break;
                case 2:
                    customerLogin(customers);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        private static void registerCustomer(Map<String, Customer> customers, Customer customer) {
            System.out.println("Enter username(At least 5 characters):");
            String username = scanner.nextLine();
            while (!customer.isValidUsername(username)) {
                System.out.println("Invalid username, please try again!");
                username = scanner.nextLine();
            }
            System.out.println("Enter password(More than 8 characters, and must contain uppercase and lowercase letters, numbers and symbols):");
            String password = scanner.nextLine();
            while (!customer.isValidPassword(password)) {
                System.out.println("Invalid password, please try again!");
                password = scanner.nextLine();
            }
            System.out.println("Enter phoneNumber:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();

            String userId = UUID.randomUUID().toString();
            customer = new Customer(username, password, phoneNumber, email, userId);
            customers.put(userId, customer);
            System.out.println("Customer registered successfully.");
        }

        private static void customerLogin(Map<String, Customer> customers) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            for (Customer customer : customers.values()) {
                try {
                    if (customer.login(username, password)) {
                        System.out.println("Login successful!");
                        customerMenu(customer);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Invalid credentials.");
        }

        private static void customerMenu(Customer customer) {
            while (true) {
                System.out.println("Customer Menu:");
                System.out.println("1. Add to Cart");
                System.out.println("2. Remove from Cart");
                System.out.println("3. Checkout");
                System.out.println("4. View Order History");
                System.out.println("5. Change Password");
                System.out.println("6. Logout");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter product details to add to cart:");
                        Product product = createProduct();
                        System.out.println("Enter quantity:");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        customer.addToCart(product, quantity);
                        break;
                    case 2:
                        System.out.println("Enter product details to remove from cart:");
                        Product removeProduct = createProduct();
                        customer.removeFromCart(removeProduct);
                        break;
                    case 3:
                        customer.checkout();
                        break;
                    case 4:
                        customer.viewOrderHistory();
                        break;
                    case 5:
                        System.out.println("Enter new password:");
                        String newPassword = scanner.nextLine();
                        customer.changePassword(newPassword);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

