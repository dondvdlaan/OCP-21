package dev.manyroads.patterns.templatemethod;

import java.util.Scanner;

/**
 * New customer
 * There are three classes: an abstract class Customer with the template method, and two concrete classes, Premium and Standard.
 * You must implement the template method in the Customer class to create an account using the following algorithm:
 * <p>
 * Verify customer's identity
 * Generate customer's identification number
 * Send a thank you message
 * Send a welcome gift
 * Your task is to make the Premium and Standard classes inherit from the class Customer, and write the methods
 * generateCustomerID() and sendGift() according to the console output.
 * <p>
 * Sample Input 1:
 * <p>
 * premium
 * Sample Output 1:
 * Verify your identity
 * Your premium account identification number: PA-01
 * Thank you for creating a new customer account!
 * You received 100 Gems
 * Sample Input 2:
 * <p>
 * standard
 * Sample Output 2:
 * <p>
 * Verify your identity
 * Your standard account identification number: ST-01
 * Thank you for creating a new customer account!
 * You received 50 Gems
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String client = scanner.next().toLowerCase();
        if ("standard".equals(client)) {
            Customer customer = new Standard();
            customer.createAccount();
        } else if ("premium".equals(client)) {
            Customer premiun = new Premium();
            premiun.createAccount();
        } else System.out.println("Error");
    }
}

abstract class Customer {
    int id;
    String preFix;
    int gift;

    public Customer(int id, String preFix, int gift) {
        this.id = id;
        this.preFix = preFix;
        this.gift = gift;
    }

    void createAccount() {
        verifyIdentity();
        generateCustomerID();
        thankYouMessage();
        sendGift();
    }

    void verifyIdentity() {
        System.out.println("Verify your identity");
    }

    abstract void generateCustomerID() ;

    void thankYouMessage() {
        System.out.println("Thank you for creating a new customer account!");
    }

    void sendGift() {
        System.out.println("You received " + gift + " Gems");
    }

    String composeId() {
        String nr = String.valueOf(id).length() == 1 ? "0" + id : String.valueOf(id);
        return preFix + "-" + nr;
    }
}

class Premium extends Customer {
    static int ID;
    final static String PREFIX = "PA";
    final static int GIFT = 100;

    public Premium() {
        super(++ID, PREFIX, GIFT);
    }

    @Override
    void generateCustomerID() {
        System.out.println("Your premium account identification number: "+composeId());
    }
}

class Standard extends Customer {
    static int ID;
    final static String PREFIX = "ST";
    final static int GIFT = 50;

    public Standard() {
        super(++ID, PREFIX, GIFT);
    }
    @Override
    void generateCustomerID() {
        System.out.println("Your standard account identification number: "+composeId());
    }
}