package dev.manyroads.projects.simplebankingsystem.stage3;

import lombok.Getter;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Objectives
 * In this stage, create a database with a table titled card. It should have the following columns:
 * <p>
 * id INTEGER
 * number TEXT
 * pin TEXT
 * balance INTEGER DEFAULT 0
 * Also, in this stage, you should read the database file name from the command line argument.
 * Filename should be passed to the program using -fileName argument, for example, -fileName db.s3db.
 * <p>
 * Pay attention: your database file should be created when the program starts, if it hasn't yet
 * been created. And all created cards should be stored in the database from now.
 */
public class Main {
    public static void main(String[] args) {
        new MyBank(args[1]).run();
    }
}

class MyBank {
    Repository repo;
    UI ui;

    public MyBank(String dbName) {
        this.repo = Repository.getInstance(dbName);
        this.ui = new UI();
    }

    public void run() {
        while (true) {
            switch (ui.mainMenu()) {
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
        CreditCard newCard = new CreditCard();
        newCard.createCreditCard();
        repo.save(newCard);
        ui.cardCreated(newCard);
    }

    void logInToAccount() {
        CreditCard existingAccount = ui.logIn();
        if (existingAccount != null) menuCheckBalance(existingAccount);
    }

    void menuCheckBalance(CreditCard extistingAccount) {
        int option = 0;
        while (option != 2) {
            switch (ui.checkBalance()) {
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

    void checkBalance(CreditCard extistingAccount) {
        ui.showBalance(extistingAccount);
    }

    void logOutAccount() {
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

    public void cardCreated(CreditCard creditCard) {
        System.out.printf("\nYour card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n", creditCard.getCreditCardNumber(), creditCard.getPin());
    }

    public CreditCard logIn() {
        System.out.println("Enter your card number:");
        String cardNr = sc.next();
        System.out.println("Enter your PIN:");
        String logInPin = sc.next();

        CreditCard existingAccount = repo.checkLogIn(cardNr, logInPin);
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

    public void showBalance(CreditCard extistingAccount) {
        System.out.println("Balance: " + extistingAccount.getBalance());
    }

    public void logOut() {
        System.out.println("You have successfully logged out!");
    }
}

@Getter
@Setter
class CreditCard extends Random {
    private final static int BIN = 400_000;
    private int accountIdentifier;
    private String creditCardNumber;
    private String pin;
    private int balance;

    public CreditCard() {
        createAccountIdentifier();
        this.balance = 0;
    }

    void createAccountIdentifier() {
        this.accountIdentifier = nextInt(100_000_000, 999_999_999 + 1);
    }

    public CreditCard createCreditCard() {
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
    static String url;
    static SQLiteDataSource dataSource;
    static Repository instance;

    private Repository() {
    }

    public static Repository getInstance(String dbName) {
        if (instance == null) {
            if (!createDB(dbName)) System.out.println("Creating DB failed");
            else instance = new Repository();
        }
        return instance;
    }

    public static Repository getInstance() {
        if (instance == null) {
            System.out.println("Repository not available!");
        }
        return instance;
    }

    static boolean createDB(String dbName) {
        boolean creationOk = true;
        url = "jdbc:sqlite:" + dbName;

        // to connect Java applications and JDBC drivers
        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS card ("
                + "	id INTEGER PRIMARY KEY,"
                + "	number text NOT NULL,"
                + "	pin text NOT NULL,"
                + "	balance INTEGER DEFAULT 0"
                + ");";

        // JDBC interface represents the connection with DBMS
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            creationOk = false;
        }
        return creationOk;
    }

    public void save(CreditCard creditCard) {

        // SQL statement for inserting new card
        String sql = "INSERT INTO card(id, number, pin, balance) VALUES(?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setInt(1, creditCard.getAccountIdentifier());
            pStmt.setString(2, creditCard.getCreditCardNumber());
            pStmt.setString(3, creditCard.getPin());
            pStmt.setInt(4, creditCard.getBalance());
            pStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    CreditCard findCardByLoginData(String creditCardNumber, String logInPin) {
        CreditCard existingCard = new CreditCard();
        // SQL statement for finding card
        String sql = "SELECT * FROM card WHERE number = ? AND pin = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setString(1, creditCardNumber);
            pStmt.setString(2, logInPin);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                existingCard.setCreditCardNumber(rs.getString("number"));
                existingCard.setPin(rs.getString("pin"));
                existingCard.setBalance(rs.getInt("balance"));
            } else {
                System.out.println("card NOT found");
                existingCard = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existingCard;
    }

    public CreditCard checkLogIn(String creditCardNumber, String logInPin) {
        return findCardByLoginData(creditCardNumber, logInPin);
    }

    String getAccountNr(String creditCardNumber) {
        return creditCardNumber.substring(6, 15);
    }
}
