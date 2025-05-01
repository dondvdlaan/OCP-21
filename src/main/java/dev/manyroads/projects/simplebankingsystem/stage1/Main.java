package dev.manyroads.projects.simplebankingsystem.stage1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Let's take a look at the anatomy of a credit card:
 * <p>
 * Credit card anatomy
 * <p>
 * The very first number is the Major Industry Identifier (MII), which tells you what sort of institution issued the card.
 * <p>
 * 1 and 2 are issued by airlines
 * 3 is issued by travel and entertainment
 * 4 and 5 are issued by banking and financial institutions
 * 6 is issued by merchandising and banking
 * 7 is issued by petroleum companies
 * 8 is issued by telecommunications companies
 * 9 is issued by national assignment
 * In our banking system, credit cards should begin with 4.
 * <p>
 * The first six digits are the Bank Identification Number (BIN). These can be used to look up where the card originated from.
 * If you have access to a list that provides detail on who owns each BIN, you can see who issued the card just by reading the card number.
 * <p>
 * Here are a few you might recognize:
 * <p>
 * Visa: 4*****
 * American Express (AMEX): 34**** or 37****
 * Mastercard: 51**** to 55****
 * In our banking system, the BIN must be 400000.
 * <p>
 * The seventh digit to the second-to-last digit is the customer account number. Most companies use just 9 digits for the account numbers,
 * but it’s possible to use up to 12. This means that using the current algorithm for credit cards, the world can issue about a trillion
 * cards before it has to change the system.
 * <p>
 * We often see 16-digit credit card numbers today, but it’s possible to issue a card with up to 19 digits using the current system.
 * In the future, we may see longer numbers becoming more common.
 * <p>
 * In our banking system, the customer account number can be any, but it should be unique. And the whole card number should be 16-digit length.
 * <p>
 * The very last digit of a credit card is the check digit or checksum. It is used to validate the credit card number using the Luhn algorithm,
 * which we will explain in the next stage of this project. For now, the checksum can be any digit you like.
 * <p>
 * Objectives
 * You should allow customers to create a new account in our banking system.
 * <p>
 * Once the program starts, you should print the menu:
 * <p>
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * <p>
 * If the customer chooses ‘Create an account’, you should generate a new card number which satisfies all the conditions described above. T
 * hen you should generate a PIN code that belongs to the generated card number. A PIN code is a sequence of any 4 digits. PIN should be
 * generated in a range from 0000 to 9999.
 * <p>
 * If the customer chooses ‘Log into account’, you should ask them to enter their card information. Your program should store all generated
 * data until it is terminated so that a user is able to log into any of the created accounts by a card number and its pin. You can use an
 * array to store the information.
 * <p>
 * After all information is entered correctly, you should allow the user to check the account balance; right after creating the account,
 * the balance should be 0. It should also be possible to log out of the account and exit the program.
 * <p>
 * Example
 * The symbol > represents the user input. Notice that it's not a part of the input.
 * <p>
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >1
 * <p>
 * Your card has been created
 * Your card number:
 * 4000004938320895
 * Your card PIN:
 * 6826
 * <p>
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * <p>
 * Enter your card number:
 * >4000004938320895
 * Enter your PIN:
 * >4444
 * <p>
 * Wrong card number or PIN!
 * <p>
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * <p>
 * Enter your card number:
 * >4000004938320895
 * Enter your PIN:
 * >6826
 * <p>
 * You have successfully logged in!
 * <p>
 * 1. Balance
 * 2. Log out
 * 0. Exit
 * >1
 * <p>
 * Balance: 0
 * <p>
 * 1. Balance
 * 2. Log out
 * 0. Exit
 * >2
 * <p>
 * You have successfully logged out!
 * <p>
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >0
 * <p>
 * Bye!
 */
public class Main {
    public static void main(String[] args) {
        new MyBank().run();
    }
}

class MyBank {
    UI ui;
    Repository repo;

    public MyBank() {
        this.ui = new UI();
        this.repo = Repository.getInstance();
    }

    public void run() {
        while (true) {
            switch (ui.mainMenu()) {
                case 1 -> createAccount();
                case 2 -> logIn();
                case 0 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("No such option!");
            }
        }
    }

    void createAccount() {
        Account newAccount = new Account();
        newAccount.createNewCreditCard();
        repo.save(newAccount);
        ui.cardCreated(newAccount);
    }

    Account logIn() {
        Account existingAccount = ui.logIn();
        if (existingAccount != null) menuCheckBalance(existingAccount);
        return null;
    }

    void menuCheckBalance(Account extistingAccount) {
        int option = 0;
        while (option != 2) {
            switch (ui.checkBalance()) {
                case 1 -> checkBalance(extistingAccount);
                case 2 -> {
                    logOut();
                    option = 2;
                }
                case 0 -> {
                    System.out.println("\nBye!");
                    System.exit(0);
                }
                default -> System.out.println("No such option!");
            }
        }
    }

    void checkBalance(Account extistingAccount) {
        ui.showBalance(extistingAccount);
    }

    void logOut() {
        ui.logOut();
    }
}

class UI {

    Scanner sc = new Scanner(System.in);
    Repository repo = Repository.getInstance();

    public int mainMenu() {
        System.out.println("""
                
                1. Create an account
                2. Log into account
                0. Exit
                """);
        return sc.nextInt();
    }

    public void cardCreated(Account account) {
        System.out.printf("\nYour card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n", account.creditCard.creditCardNumber, account.creditCard.pin);
    }

    public Account logIn() {
        System.out.println("Enter your card number:");
        String cardNr = sc.next();
        System.out.println("Enter your PIN:");
        String logInPin = sc.next();

        Account existingAccount = repo.checkLogIn(cardNr, logInPin);
        System.out.println("existingAccount "+existingAccount);
        if (existingAccount != null) {
            System.out.println("You have successfully logged in!");
            return existingAccount;
        } else {
            System.out.println("Wrong card number or PIN!");
        }
        return null;
    }

    public int checkBalance() {
        System.out.println("""
                
                1. Balance
                2. Log out
                0. Exit
                """);
        return sc.nextInt();
    }

    public void showBalance(Account extistingAccount) {
        System.out.println("Balance: " + extistingAccount.balance);
    }

    public void logOut() {
        System.out.println("You have successfully logged out!");
    }
}

class Account {
    String customerAccountNumber;
    CreditCard creditCard;
    BigDecimal balance;

    public Account() {
        createCustomerAccountNumber();
        this.balance = new BigDecimal(0);
    }

    void createCustomerAccountNumber() {
        this.customerAccountNumber = String.format("%09d", new Random().nextInt(999999999 + 1));
    }

    public void createNewCreditCard() {
        this.creditCard = new CreditCard().createCreditCard(customerAccountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "customerAccountNumber=" + customerAccountNumber +
                ", creditCard PIN=" + creditCard.pin +
                '}';
    }
}

class CreditCard {
    final static int BIN = 400_000;

    int checksum;
    String creditCardNumber;
    String pin;


    public CreditCard createCreditCard(String customerAccountNumber) {
        this.creditCardNumber = String.format("%06d%s%d", BIN, customerAccountNumber, getChecksum());
        this.pin = generatePin();
        return this;
    }

    int getChecksum() {
        return new Random().nextInt(9 + 1);
    }

    String generatePin() {
        int temp = new Random().nextInt(9999 + 1);
        return String.format("%04d", temp);
    }
}

class Repository {
    static Repository instance;
    List<Account> accounts = new ArrayList<>();

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void save(Account account) {
        accounts.add(account);
    }

    public Account getAccontByAccountNr(String customerAccountNumber) {
        List<Account> lAccount = accounts.stream().filter(a -> a.customerAccountNumber.equals(customerAccountNumber)).toList();
        if (lAccount.size() == 1) return lAccount.getFirst();
        return null;
    }

    public Account checkLogIn(String creditCardNumber, String logInPin) {
        String sAccountNr = getAccountNr(creditCardNumber);
        Account account = getAccontByAccountNr(sAccountNr);
        if (account != null && account.creditCard.pin.equals(logInPin)) return account;
        return null;
    }

    String getAccountNr(String creditCardNumber) {
        return creditCardNumber.substring(6, 15);
    }
}
