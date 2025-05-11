package dev.manyroads.projects.simplebankingsystem.stage4;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * You have created the foundation of our banking system. Now let's take the opportunity to deposit money into an
 * account, make transfers and close an account if necessary.
 * <p>
 * Now your menu should look like this:
 * <p>
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * <p>
 * If the user asks for Balance, you should read the balance of the account from the database and output it into
 * the console.
 * <p>
 * Add income item should allow us to deposit money to the account.
 * Do transfer item should allow transferring money to another account. You should handle the following errors:
 * <p>
 * If the user tries to transfer more money than he/she has, output: Not enough money!
 * If the user tries to transfer money to the same account, output the following message:
 * You can't transfer money to the same account!
 * If the receiver's card number doesn’t pass the Luhn algorithm, you should output:
 * Probably you made a mistake in the card number. Please try again!
 * If the receiver's card number doesn’t exist, you should output: Such a card does not exist.
 * If there is no error, ask the user how much money they want to transfer and make the transaction.
 * If the user chooses the Close an account item, you should delete that account from the database.
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
        if (existingAccount != null) menuOperations(existingAccount);
    }

    void menuOperations(CreditCard extistingAccount) {
        boolean terminated = false;
        while (!terminated) {
            switch (ui.operationsMenu()) {
                case 1 -> ui.showBalance(extistingAccount);
                case 2 -> ui.addIncome(extistingAccount);
                case 3 -> ui.doTransfer(extistingAccount);
                case 4 -> {
                    ui.closeAcount(extistingAccount);
                    terminated = true;
                }
                case 5 -> {
                    ui.logOut();
                    terminated = true;
                }
                case 0 -> {
                    System.out.println("\nBye!");
                    System.exit(0);
                }
                default -> System.out.println("No such option!");
            }
        }
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

    public int operationsMenu() {
        System.out.println("""
                
                1. Balance
                2. Add income
                3. Do transfer
                4. Close account
                5. Log out
                0. Exit
                """);
        return sc.nextInt();
    }

    public void showBalance(CreditCard extistingAccount) {
        System.out.println("Balance: " +
                repo.findCardByCardNr(extistingAccount.getCreditCardNumber()).getBalance());
    }

    public void addIncome(CreditCard extistingAccount) {
        System.out.println("Enter income: ");
        extistingAccount.setBalance(extistingAccount.getBalance() + sc.nextInt());
        repo.updateCard(extistingAccount);
    }

    public void doTransfer(CreditCard extistingAccount) {
        System.out.println("Transfer");
        System.out.println("Enter card number: ");
        String targetCardNr = sc.next();

        if (!repo.checkCardExist(targetCardNr)) {
            System.out.println("Such a card does not exist");
            return;
        }
        if (extistingAccount.getCreditCardNumber().equals(targetCardNr)) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }
        if (!extistingAccount.checkLuhnAlgorithm(targetCardNr)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        System.out.println("Enter how much money you want to transfer:");
        int moneyToTransfer = sc.nextInt();
        if (moneyToTransfer > extistingAccount.getBalance()) {
            System.out.println("Not enough money!");
            return;
        }
        CreditCard targetCard = repo.findCardByCardNr(targetCardNr);
        if (repo.transferMoney(extistingAccount, targetCard, moneyToTransfer)) System.out.println("Success!");
        else System.out.println("Transfer not successful!");

    }

    public void closeAcount(CreditCard extistingAccount) {
        if (repo.checkCardExist(extistingAccount.getCreditCardNumber())) {
            if (repo.removeCard(extistingAccount)) System.out.println("The account has been closed!");
            else System.out.println("Closure of account failed!");
        }
    }

    public void logOut() {
        System.out.println("You have successfully logged out!");
    }
}

class CreditCard extends Random {
    private final static int BIN = 400_000;
    private int accountIdentifier;
    private String creditCardNumber;
    private String pin;
    private int balance;

    public CreditCard() {
        createAccountIdentifier();
        this.balance = 1000;
    }

    public CreditCard(int accountIdentifier, String creditCardNumber, String pin, int balance) {
        this.accountIdentifier = accountIdentifier;
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(int accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    void createAccountIdentifier() {
        this.accountIdentifier = nextInt(100_000_000, 999_999_999 + 1);
    }


    public CreditCard createCreditCard() {
        StringBuilder sRawCreditCardNr = new StringBuilder();
        sRawCreditCardNr.append(BIN);
        sRawCreditCardNr.append(accountIdentifier);
        this.creditCardNumber = String.format("%s%d", sRawCreditCardNr, getChecksum(sRawCreditCardNr.toString()));
        //if (creditCardNumber.length() > 16) System.out.println("Larger than 16 " + creditCardNumber);
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
        if (addAllNumbers % 10 == 0) return 0;
        else return 10 - addAllNumbers % 10;
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

    boolean checkLuhnAlgorithm(String targetCardNr) {
        String rawTragetCardNr = targetCardNr.substring(0, targetCardNr.length() - 1);
        String targetCardChecksum = targetCardNr.substring(targetCardNr.length() - 1);
        String luhnChacksum = String.valueOf(getChecksum(rawTragetCardNr));
        return targetCardChecksum.equals(luhnChacksum);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "accountIdentifier=" + accountIdentifier +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
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
        String sql = """
                INSERT INTO card(id, number, pin, balance) 
                VALUES(?, ?, ?, ?)
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setInt(1, creditCard.getAccountIdentifier());
            pStmt.setString(2, creditCard.getCreditCardNumber());
            pStmt.setString(3, creditCard.getPin());
            pStmt.setInt(4, creditCard.getBalance());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCard(CreditCard creditCard) {
        // SQL statement for updating card
        String sql = """    
                UPDATE card
                SET balance = ?
                WHERE id = ?
                """;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setInt(1, creditCard.getBalance());
            pStmt.setInt(2, creditCard.getAccountIdentifier());
            System.out.println("updateCard " + pStmt.executeUpdate());
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
                existingCard.setAccountIdentifier(rs.getInt("id"));
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

    CreditCard findCardByCardNr(String creditCardNumber) {
        CreditCard existingCard = new CreditCard();
        // SQL statement for finding card
        String sql = "SELECT * FROM card WHERE number = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setString(1, creditCardNumber);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                existingCard = new CreditCard(
                        rs.getInt("id"),
                        rs.getString("number"),
                        rs.getString("pin"),
                        rs.getInt("balance")
                );
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

    boolean checkCardExist(String targetCardNr) {
        boolean doesExist = false;
        // SQL statement for finding card
        String sql = "SELECT * FROM card WHERE number = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setString(1, targetCardNr);
            ResultSet rs = pStmt.executeQuery();
            doesExist = rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return doesExist;
    }

    public boolean transferMoney(CreditCard sourceCard, CreditCard targetCard, int moneyToTransfer) {
        boolean isSucces = false;
        String sql = "UPDATE card SET balance = ? WHERE id = ?";

        try (Connection con = dataSource.getConnection()) {
            // Disable auto-commit mode
            con.setAutoCommit(false);

            try (PreparedStatement pStmtSourceCard = con.prepareStatement(sql);
                 PreparedStatement pStmtTargetCard = con.prepareStatement(sql)) {

                pStmtSourceCard.setInt(1, sourceCard.getBalance() - moneyToTransfer);
                pStmtSourceCard.setInt(2, sourceCard.getAccountIdentifier());
                int rowCountSource = pStmtSourceCard.executeUpdate();

                pStmtTargetCard.setInt(1, targetCard.getBalance() + moneyToTransfer);
                pStmtTargetCard.setInt(2, targetCard.getAccountIdentifier());
                int rowCountTarget = pStmtTargetCard.executeUpdate();

                con.commit();
                isSucces = rowCountSource == 1 && rowCountTarget == 1;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSucces;
    }

    public boolean removeCard(CreditCard extistingAccount) {
        boolean isSucces = false;
        String sql = """
                DELETE FROM card
                WHERE number = ?
                """;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            pStmt.setString(1, extistingAccount.getCreditCardNumber());
            isSucces = pStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isSucces;
    }
}
