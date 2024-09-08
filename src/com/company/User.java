package com.company;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;
import java.util.Scanner;

@Getter
@Setter
@ToString
abstract class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean isLocked = false;
    private int failedLoginAttempts = 0;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public User(String username, String password, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public boolean isValidUsername(String username) {
        return username.length() >= 5;
    }

    public boolean isValidPassword(String password) {
        return password.length() > 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[\\W_].*");
    }

    public String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    public void changePassword(String newPassword) {
        if (isValidPassword(newPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully!");
        }
        while (!isValidPassword(newPassword)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Invalid password, please enter again!");
            newPassword = scanner.nextLine();
        }
    }

    public boolean login(String inputUsername, String inputPassword) throws Exception {
        if (isLocked) {
            throw new Exception("The account is locked, please reset the password!");
        }
        if (this.getUsername().equals(inputUsername) && this.getPassword().equals(inputPassword)) {
            System.out.println("Login successfully!");
            failedLoginAttempts = 0;
            return true;
        } else {
            System.out.println("Incorrect username or password!");
            failedLoginAttempts++;
            if (failedLoginAttempts >= 5) {
                isLocked = true;
                throw new Exception("Your account is locked, because you have entered password incorrectly 5 times!");
            }
        }
        return false;
    }
}


