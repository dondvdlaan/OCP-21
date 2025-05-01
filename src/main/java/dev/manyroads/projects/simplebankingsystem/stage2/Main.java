package dev.manyroads.projects.simplebankingsystem.stage2;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * In this stage, we will find out what the purpose of the checksum is and what the Luhn algorithm is used for.
 * <p>
 * The main purpose of the check digit is to verify that the card number is valid. Say you're buying something online, and you
 * type in your credit card number incorrectly by accidentally swapping two digits, which is one of the most common errors. When
 * the website looks at the number you've entered and applies the Luhn algorithm to the first 15 digits, the result won't match the
 * 16th digit on the number you entered. The computer knows the number is invalid, and it knows the number will be rejected if it
 * tries to submit the purchase for approval, so you're asked to re-enter the number. Another purpose of the check digit is to catch
 * clumsy attempts to create fake credit card numbers. Those who are familiar with the Luhn algorithm, however, could get past this
 * particular security measure.
 * Luhn Algorithm in action
 * <p>
 * The Luhn algorithm is used to validate a credit card number or other identifying numbers, such as Social Security. Luhn algorithm,
 * also called the Luhn formula or modulus 10, checks the sum of the digits in the card number and checks whether the sum matches the
 * expected result or if there is an error in the number sequence. After working through the algorithm, if the total modulus 10 equals
 * zero, then the number is valid according to the Luhn method.
 * <p>
 * While the algorithm can be used to verify other identification numbers, it is usually associated with credit card verification.
 * The algorithm works for all major credit cards.
 * <p>
 * Here is how it works for a credit card with the number 4000008449433403:
 * <p>
 * Luhn Algorithm for a credit card 4000008449433403
 * <p>
 * If the received number is divisible by 10 with the remainder equal to zero, then this number is valid; otherwise, the card number is not valid. When registering in your banking system, you should generate cards with numbers that are checked by the Luhn algorithm. You know how to check the card for validity. But how do you generate a card number so that it passes the validation test? It's very simple!
 * <p>
 * First, we need to generate an Account Identifier, which is unique to each card. Then we need to assign the Account Identifier to our BIN (Bank Identification Number). As a result, we get a 15-digit number 400000844943340, so we only have to generate the last digit, which is a checksum.
 * <p>
 * To find the checksum, it is necessary to find the control number for 400000844943340 by the Luhn algorithm. It equals 57 (from the example above). The final check digit of the generated map is 57+X, where X is checksum. In order for the final card number to pass the validity check, the check number must be a multiple of 10, so 57+X must be a multiple of 10. The only number that satisfies this condition is 3.
 * <p>
 * Therefore, the checksum is 3. So the total number of the generated card is 4000008449433403. The received card is checked by the Luhn algorithm.
 * <p>
 * You need to change the credit card generation algorithm so that they pass the Luhn algorithm.
 * <p>
 * Objectives
 * You should allow customers to create a new account in our banking system.
 * <p>
 * Once the program starts you should print the menu:
 * <p>
 * 1. Create an account
 * 2. Log into the account
 * 0. Exit
 * <p>
 * If the customer chooses ‘Create an account’, you should generate a new card number that satisfies all the conditions described above. Then you should generate a PIN code that belongs to the generated card number. A PIN is a sequence of 4 digits; it should be generated in the range from 0000 to 9999.
 * <p>
 * If the customer chooses ‘Log into account’, you should ask to enter card information.
 * <p>
 * After the information has been entered correctly, you should allow the user to check the account balance; after creating the account, the balance should be 0. It should also be possible to log out of the account and exit the program.
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

class MyBank extends UI {
    Repository repo;

    public MyBank() {
        this.repo = Repository.getInstance();
    }

    public void run() {
        while (true) {
            switch (mainMenu()) {
                case 1 -> createAccount();
                case 2 -> logInToAccount();
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
        cardCreated(newAccount);
    }

    void logInToAccount() {
        Account existingAccount = logIn();
        if (existingAccount != null) menuCheckBalance(existingAccount);
    }

    void menuCheckBalance(Account extistingAccount) {
        int option = 0;
        while (option != 2) {
            switch (checkBalance()) {
                case 1 -> checkBalance(extistingAccount);
                case 2 -> {
                    logOutAccount();
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
        showBalance(extistingAccount);
    }

    void logOutAccount() {
        logOut();
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
                "%s\n", account.creditCard.getCreditCardNumber(), account.creditCard.getPin());
    }

    public Account logIn() {
        System.out.println("Enter your card number:");
        String cardNr = sc.next();
        System.out.println("Enter your PIN:");
        String logInPin = sc.next();

        Account existingAccount = repo.checkLogIn(cardNr, logInPin);
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
        System.out.println("Balance: " + extistingAccount.getBalance());
    }

    public void logOut() {
        System.out.println("You have successfully logged out!");
    }
}

@Getter
class Account extends Random {
    private String accountIdentifier;
    private BigDecimal balance;
    CreditCard creditCard;

    public Account() {
        createAccountIdentifier();
        this.balance = new BigDecimal(0);
    }

    void createAccountIdentifier() {
        this.accountIdentifier = String.valueOf(nextInt(100_000_000, 999_999_999 + 1));
    }

    public void createNewCreditCard() {
        this.creditCard = new CreditCard().createCreditCard(accountIdentifier);
    }


    @Override
    public String toString() {
        return "Account{" +
                "customerAccountNumber=" + accountIdentifier +
                ", creditCard PIN=" + creditCard.getPin() +
                '}';
    }
}

@Getter
class CreditCard {
    private final static int BIN = 400_000;

    private String creditCardNumber;
    private String pin;

    public CreditCard createCreditCard(String accountIdentifier) {
        StringBuilder sRawCreditCardNr = new StringBuilder();
        sRawCreditCardNr.append(BIN);
        sRawCreditCardNr.append(accountIdentifier);
        this.creditCardNumber = String.format("%s%d", sRawCreditCardNr, getChecksum(sRawCreditCardNr.toString()));
        this.pin = generatePin();
        return this;
    }

    long getChecksum(String sRawCreditCardNr) {
        int[] iRawCreditCardNr = convertToArray(sRawCreditCardNr);
        long addAllNumbers = IntStream.range(0, iRawCreditCardNr.length)
                // multiply odd digits by 2
                .map(i -> i % 2 == 0 ? iRawCreditCardNr[i] *= 2 : iRawCreditCardNr[i])
                // substract 9 from numbers over 9
                .map(i -> i > 9 ? i -= 9 : i)
                .sum();
        long tenths = addAllNumbers / 10 + 1;
        return tenths * 10 - addAllNumbers;
    }

    int[] convertToArray(String sRawCreditCardNr) {
        int[] iRawCreditCardNr = new int[sRawCreditCardNr.length()];
        for (int i = 0; i < sRawCreditCardNr.length(); i++) {
            iRawCreditCardNr[i] = Integer.parseInt(sRawCreditCardNr.substring(i, i + 1));
        }
        return iRawCreditCardNr;
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
        List<Account> lAccount = accounts.stream().filter(a -> a.getAccountIdentifier().equals(customerAccountNumber)).toList();
        if (lAccount.size() == 1) return lAccount.getFirst();
        return null;
    }

    public Account checkLogIn(String creditCardNumber, String logInPin) {
        String sAccountNr = getAccountNr(creditCardNumber);
        Account account = getAccontByAccountNr(sAccountNr);
        if (account != null && account.creditCard.getPin().equals(logInPin)) return account;
        return null;
    }

    String getAccountNr(String creditCardNumber) {
        return creditCardNumber.substring(6, 15);
    }
}
